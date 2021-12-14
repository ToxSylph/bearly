package com.toxsylph.bearly.template;

import java.util.ArrayList;

import com.toxsylph.bearly.setting.Setting;

@SuppressWarnings("rawtypes")
public class TModule {

	public enum Category {
		PLAYER, COMBAT, EXPLOIT, MOVEMENT, RENDER, WORLD, CLIENT, CHAT
	}

	protected String name;
	protected int key;
	protected boolean toggled;
	protected String description;
	protected Category category;
	protected ArrayList<Setting> settings;

	public TModule(String name, int key, boolean toggled, String description, Category category) {
		super();
		this.name = name;
		this.key = key;
		this.toggled = toggled;
		this.description = description;
		this.category = category;
		settings = new ArrayList<Setting>();
	}

	public void toggle() {
		if (toggled) {
			disable();
		} else {
			enable();
		}
	}

	public void enable() {
		this.toggled = true;
		onEnable();
	}

	public void disable() {
		this.toggled = false;
		onDisable();
	}

	protected void onEnable() {
	}

	protected void onDisable() {
	}

	public void update() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ArrayList<Setting> getSettings() {
		return settings;
	}

	public void setSettings(ArrayList<Setting> settings) {
		this.settings = settings;
	}

	public void addSetting(Setting setting) {
		this.settings.add(setting);
	}

	public Setting getSetting(String name) {
		for (Setting s : settings) {
			if (s.getName().equalsIgnoreCase(name))
				return s;
		}
		return null;
	}
}
