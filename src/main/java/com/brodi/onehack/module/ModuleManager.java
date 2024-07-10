package com.brodi.onehack.module;
import com.brodi.onehack.movement.Flight;
import com.brodi.onehack.movement.Sprint;
import com.brodi.onehack.module.Mod;

import java.util.List;
import java.util.ArrayList;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private  List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }

    public List<Mod> getModules() {
        return modules;
    }

    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();
        for (Mod module : modules) {
            if (module.isEnabled()) enabled.add(module);
        }
        return enabled;
    }
    public List<Mod> getModulesInCategory(Mod.Category category) {
        List<Mod> categoryModules = new ArrayList<>();

        for (Mod mod : modules) {
            if (mod.getCategory() == category) {
                categoryModules.add(mod);
            }
        }

        return categoryModules;
    }

    private void addModules() {
        modules.add(new Flight());
        modules.add(new Sprint());
    }



}
