package javastudy.designpattern.singleton.brokepattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 이 실험을 위해서 SingletonClass 를 생성.
 * test 를 위해서는 SingletonClass 에서 Serializable 을 implements 해야 한다.
 */
public class Serializing {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SingletonClass singleton = SingletonClass.getInstance();
		SingletonClass instance = null;

		// singleton object 를 file 에 저장 : serialization
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.obj"))) {
			out.writeObject(singleton);
		}

		// file 에 저장된 객체를 read : deserialization
		// 이 때 생성자를 사용하여 instance 를 만들어주기 때문에 다른 instance 가 나오는 것이다.
		try (ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.obj"))) {
			instance = (SingletonClass) in.readObject();
		}

		System.out.println("serialization object : " + singleton);
		System.out.println("deserialization object : " + instance);
		/*
		serialization object : javastudy.designpattern.singleton.brokepattern.SingletonClass@234bef66
		deserialization object : javastudy.designpattern.singleton.brokepattern.SingletonClass@7c16905e
		 */
	}
}
