package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Upload_File {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public byte[] transferImg(String fileName) {
		//��ͼƬת��Ϊ������
		File file = new File(fileName); //��ȡ����������ͼƬ��url 
		byte[] content=new byte[0];
		try {
			//���ļ�
			FileInputStream fin = new FileInputStream(file);
			//��һ�����屣������
			ByteBuffer nbf = ByteBuffer.allocate((int) file.length());
			byte[] array = new byte[1024];
			int offset = 0, length = 0;
			//��������
			while ((length = fin.read(array)) > 0) {
				if (length != 1024)
					nbf.put(array, 0, length);
				else
					nbf.put(array);
				offset += length;
			}
			//�½�һ�����鱣��Ҫд������
			content = nbf.array();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
