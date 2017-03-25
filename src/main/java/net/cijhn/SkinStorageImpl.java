package net.cijhn;

import riskyken.armourersWorkshop.api.common.skin.data.ISkin;
import riskyken.armourersWorkshop.client.render.model.bake.ModelBakery;
import riskyken.armourersWorkshop.common.skin.data.Skin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ci010
 */
public class SkinStorageImpl implements SkinRepository {
    private Map<Object, ISkin> skinHashMap;

    public SkinStorageImpl() {
        this.skinHashMap = new HashMap<Object, ISkin>();
    }

    @Override
    public boolean registerSkin(Object key, ISkin skin) {
        if (skinHashMap.containsKey(key))
            return false;
        skinHashMap.put(key, skin);
        ModelBakery.INSTANCE.receivedUnbakedModel((Skin) skin);
        return true;
    }

    @Override
    public Collection<ISkin> getAllStorage() {
        return skinHashMap.values();
    }

    @Override
    public ISkin getSkin(Object key) {
        return skinHashMap.get(key);
    }
}