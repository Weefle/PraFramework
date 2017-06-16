package de.zortax.pra.network.serialization;//  Created by leo on 16.06.17.

import de.zortax.pra.network.error.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Serializer {

    private boolean useFieldNames;
    private MappingManager mappingManager;

    Serializer(boolean useFieldNames, MappingManager mappingManager) {
        this.useFieldNames = useFieldNames;
        this.mappingManager = mappingManager;
    }


    public byte[] serialize(Object obj) {
        return serialize(obj, null);
    }

    public byte[] serialize(Object obj, String name) {

        try {

            if (mappingManager.isCustomMapped(obj)) {
                TypeMapping tm = useFieldNames && name != null ? mappingManager.getMappingFor(obj, name) : mappingManager.getMappingFor(obj);
                return mappingManager.getSerializer(obj.getClass()).getBytes(obj, tm);
            } else {

                ArrayList<Byte> allBytes = new ArrayList<>();
                if (useFieldNames && name != null) {
                    for (byte b : mappingManager.getDataTypeCode(name))
                        allBytes.add(b);
                } else {
                    for (byte b : mappingManager.getDataTypeCode())
                        allBytes.add(b);
                }
                ArrayList<Field> allFields = new ArrayList<>();
                allFields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
                Class currentClass = obj.getClass();
                while (currentClass.getSuperclass() != null) {
                    currentClass = currentClass.getSuperclass();
                    allFields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
                }

                for (Field f : allFields) {
                    for (byte b : useFieldNames ? serialize(f.get(obj), f.getName()) : serialize(f.get(obj))) {
                        allBytes.add(b);
                    }
                }

                for (byte b : mappingManager.getDataTypeCloser())
                    allBytes.add(b);

                byte[] bytes = new byte[allBytes.size()];
                for (int i = 0; i < allBytes.size(); i++)
                    bytes[i] = allBytes.get(i);

                return bytes;
            }

        } catch (Exception e) {
            ExceptionHandler.addException(e);
        }

        return null;
    }

    public <T>  T deserialize(byte[] data, Class<T> type) {

        return null;
    }

}
