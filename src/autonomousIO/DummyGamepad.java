package autonomousIO;

import java.io.File;
import java.util.Collections;
import java.util.stream.Collectors;

import enums.ControllerType;
import enums.Directions;
import interfaces.Gamepad;
import wrapper.Timer;
import wrapper.ToggleButton;

public class DummyGamepad implements Gamepad{

	protected ToggleButton[] buttons;
	
	protected FileManager fileManager;
	protected DataParser parse;
	protected String s;
	protected int index = 0;
	protected int port;
	protected Timer timer;
	
	protected int controllerCount = 1;

	public DummyGamepad(int port, FileManager fileManager){
		this.fileManager = fileManager;
		parse = new DataParser();
		this.port = port;
		timer = new Timer();
		timer.start();
		updateData();
		
		buttons = new ToggleButton[parse.buttons.size()];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new ToggleButton();
		}
	}
	
	public void updateData(){
		s = fileManager.lines.get(index);
		
		if(index < (fileManager.lines.size()-1)){
			parse.getControllerData(s);
			if(timer.wait(parse.time)){
				while(parse.port != port){
					System.out.println(parse.port);
					if(parse.port == -1)
						return;
					controllerCount++;
					
					if(controllerCount > 6)
						return;
					
					index++;
					
					s = fileManager.lines.get(index);
				}
				
				index += controllerCount;
			}
		}else{
			Collections.fill(parse.axes, 0.0);
			Collections.fill(parse.buttons, false);
		}
	}
	
	public void resetIndex(){
		index = 0;
	}
	
	@Override
	public double getAxis(int axis) {
		return parse.axes.get(axis);
	}

	@Override
	public boolean getButton(int button) {
		return parse.buttons.get(button);
	}
	
	@Override
	public boolean getToggle(int button) {
		return buttons[button].getDownToggle(parse.buttons.get(button));
	}

	@Override
	public boolean getButtonUp(int button) {
		return buttons[button].getUp(parse.buttons.get(button));
	}

	@Override
	public boolean getButtonDown(int button) {
		return buttons[button].getDown(parse.buttons.get(button));
	}

	@Override
	public boolean getPOV(Directions direction){
		return (parse.pov == direction.ordinal() * 45);
	}

	@Override
	public ControllerType getControllerType(){
		if(buttons == null){
			return ControllerType.GP;
		}
		
		for(ControllerType type : ControllerType.values()){
			if(type.getButtonCount() == parse.buttons.size()){
				return type;
			}
		}
		return ControllerType.GP;
	}

	@Override
	public int getButtonCount() {
		return parse.buttons.size();
	}
}
