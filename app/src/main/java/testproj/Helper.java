package testproj;

import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import org.lwjgl.BufferUtils;

public class Helper {
    public static ByteBuffer storeArrayInBuffer(double[] array) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(array.length * 8);
		
		for(double i : array) {
			buffer.putDouble(i);
		}
		
		buffer.position(0);
		
		return buffer;
	}
    
    public static ByteBuffer storeArrayInBuffer(int[] array) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(array.length * 4);
		
		for(int i : array) {
			buffer.putInt(i);
		}
		
		buffer.position(0);
		
		return buffer;
	}

	public static int loadShader(File file, int type) {
		try {
			Scanner sc = new Scanner(file);
			StringBuilder data = new StringBuilder();
			
			if(file.exists()) {
				while(sc.hasNextLine()) {
					data.append(sc.nextLine() + "\n");
				}
				
				sc.close();
			}
			int id = GL30.glCreateShader(type);
			GL30.glShaderSource(id, data);
			GL30.glCompileShader(id);
			return id;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
}