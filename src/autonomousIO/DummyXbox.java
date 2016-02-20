package autonomousIO;

import java.util.Collections;

import Enums.*;
import interfaces.XboxController;
import wrapper.Axes;

/**
 * Congratulations, we are now the proud owners of a robot that can drive itself :) almost...
 * @author Ghjf544912
 */
public class DummyXbox extends DummyGamepad implements  XboxController{
		
	public DummyXbox(FileManager fileManager){
		super(fileManager);
	}
	
	/**
	 * @return Returns the axis values of the left stick.
	 */
	public Axes getLeftStick(){
		return new Axes(parse.axes.get(0), parse.axes.get(1));
	}
	
	/**
	 * @return Returns axis values of the right stick.
	 */
	public Axes getRightStick(){
		return new Axes(parse.axes.get(4), parse.axes.get(5));
	}
	
	/**
	 * @return Returns the axis value of the left trigger button.
	 */
	public double getLeftTrigger(){ 
		return parse.axes.get(2);
	}
	
	/**
	 * @return Returns the axis value of the right trigger button.
	 */
	public double getRightTrigger(){ 
		return parse.axes.get(3);
	}

	@Override
	public boolean getButton(XboxButtons button) {
		return getButton(button.ordinal());
	}

	@Override
	public boolean getButtonDown(XboxButtons button) {
		return getButtonDown(button.ordinal());
	}

	@Override
	public boolean getButtonUp(XboxButtons button) {
		return getButtonUp(button.ordinal());
	}
	
	@Override
	public boolean getDPad(Directions direction) {
		return getPOV(direction);
	}

}