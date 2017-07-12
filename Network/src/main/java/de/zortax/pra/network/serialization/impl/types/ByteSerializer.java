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

package de.zortax.pra.network.serialization.impl.types;//  Created by leo on 12.07.17.

import de.zortax.pra.network.serialization.FieldSerializer;
import de.zortax.pra.network.serialization.impl.TypeCodes;
import de.zortax.pra.network.serialization.impl.Util;

import java.lang.reflect.Field;

public class ByteSerializer implements FieldSerializer<Byte> {

    @Override
    public byte[] toBytes(Field f, Object instance) throws IllegalAccessException {
        return Util.toBytes(TypeCodes.BYTE, f.getName(), new byte[]{(byte) f.get(instance)});
    }

    @Override
    public String getFieldName(byte[] bytes) {
        return Util.getFieldName(bytes);
    }

    @Override
    public Byte getValue(byte[] bytes) {
        return bytes[bytes.length - 1];
    }

    @Override
    public byte[] getBlockFrom(byte[] allData, int index) {
        return Util.getBlock(allData, index, 1);
    }
}
