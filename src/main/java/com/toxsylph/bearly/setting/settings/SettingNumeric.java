package com.toxsylph.bearly.setting.settings;

import com.toxsylph.bearly.setting.Setting;

public class SettingNumeric<T> extends Setting<T> {

	private final T minValue;
	private final T maxValue;
	private final T minValueCmd;
	private final T maxValueCmd;

	public SettingNumeric(String name, T value, T minValue, T maxValue, T minValueCmd, T maxValueCmd) {
		super(name, value);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minValueCmd = minValueCmd;
		this.maxValueCmd = maxValueCmd;
	}

	public T getMinValue() {
		return minValue;
	}

	public T getMaxValue() {
		return maxValue;
	}

	public T getMinValueCmd() {
		return minValueCmd;
	}

	public T getMaxValueCmd() {
		return maxValueCmd;
	}

}
