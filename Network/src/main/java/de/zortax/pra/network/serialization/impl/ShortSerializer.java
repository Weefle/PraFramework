/*

    PraFramework - A simple TCP-Networking framework for Java
    Copyright (C) 2017  Zortax

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package de.zortax.pra.network.serialization.impl;// Created by leo on 18.06.17

import de.zortax.pra.network.serialization.ClassSerializer;
import de.zortax.pra.network.serialization.SerializationUtils;
import de.zortax.pra.network.serialization.TypeMapping;

import java.util.ArrayList;

public class ShortSerializer implements ClassSerializer<Short> {

    @Override
    public byte[] getBytes(Short instance, TypeMapping mapping) throws Exception {
        ArrayList<Byte> bytes = new ArrayList<>();
        for (int i = (int) mapping.getValueSize() - 1; i >= 0; i++)
            bytes.add((byte) (instance >> (i * 8)));
        return SerializationUtils.toByteArray(bytes);
    }

    @Override
    public Short getInstance(byte[] bytes) throws Exception {
        switch (bytes.length) {
            case 2:
                return (short) (bytes[0] << 8 | (bytes[1] & 0xFF));
            case 1:
                return (short) bytes[0];
            default:
                throw new IllegalArgumentException("Illegal byte array size!");
        }
    }

}
