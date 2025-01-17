/*
 * This file is part of  GottschCore.
 * Copyright (c) 2025 Mark Gottschling (gottsch)
 *
 * All rights reserved.
 *
 * GottschCore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GottschCore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GottschCore.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.fabric.gottschcore.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import mod.gottsch.fabric.gottschcore.GottschCore;

import java.util.*;

/**
 * A simple HashBiMap-backed registry.
 * NOTE even though a HashBiMap allows nulls, this Registry DOES NOT.
 * @author Mark Gottschling on 1/16/2025
 * @since 3.0.0
 */
public class SimpleRegistry<K, V> {

    private final BiMap<K, V> registry = HashBiMap.create();

    /**
     * A basic wrapper for BiMap.putIfAbsent()
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return empty Optional if not previously associated, else returns the current value.
     */
    public Optional<V> register(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("registry does not accept null for neither key nor value.");
        }
        return Optional.ofNullable(registry.put(key, value));
    }

    public Optional<V> unregister(K key) {
        return Optional.ofNullable(registry.remove(key));
    }

    /**
     * A basic wrapper for BiMap.put().
     * @param key key with which the specified value is to be associated
     *
     * @param value value to be associated with the specified key
     * @return an Optional of the previous value associated with key, or empty Optional if there was no mapping for key.
     */
    public Optional<V> forceRegister(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("registry does not accept null for neither key nor value.");
        }
        if (registry.containsValue(value)) {
            GottschCore.LOGGER.warn("replacing entry for key -> {}, value -> {} with new value -> {}", key, registry.get(key), value);
        }
        return Optional.ofNullable(registry.forcePut(key, value));
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(registry.get(key));
    }

    public Optional<K> getKey(V value) {
        return Optional.ofNullable(registry.inverse().get(value));
    }

    /**
     *
     * @return a copy of the registry's keys. any changes to the key list will not reflect in the registry.
     */
    public List<K> getKeys() {
        return new ArrayList<>(registry.keySet());
    }

    /**
     *
     * @return a copy of the registry's values. any changes to the value list will not reflect in the registry.
     */
    public List<V> getValues() {
        return new ArrayList<>(registry.values());
    }

    /**
     *
     * @return a copy of the registry's entries. any changes to the entry set will not reflect in the registry.
     */
    public Set<Map.Entry<K, V>> getEntries() {
        return new HashSet<>(registry.entrySet());
    }

    public void clear() {
        registry.clear();
    }
}
