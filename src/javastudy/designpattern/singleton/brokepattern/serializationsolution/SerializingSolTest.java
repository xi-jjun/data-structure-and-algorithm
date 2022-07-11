package javastudy.designpattern.singleton.brokepattern.serializationsolution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializingSolTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerializationSolutionSingleton singleton = SerializationSolutionSingleton.getInstance();
		SerializationSolutionSingleton instance = null;

		// singleton object 를 file 에 저장 : serialization
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("sol_singleton.obj"))) {
			out.writeObject(singleton);
		}

		// file 에 저장된 객체를 read : deserialization
		// 이 때 생성자를 사용하여 instance 를 만들어주기 때문에 다른 instance 가 나오는 것이다.
		try (ObjectInput in = new ObjectInputStream(new FileInputStream("sol_singleton.obj"))) {
			instance = (SerializationSolutionSingleton) in.readObject();
		}

		System.out.println("serialization object : " + singleton);
		System.out.println("deserialization object : " + instance);
		/*
		serialization object : javastudy.designpattern.singleton.brokepattern.serializationsolution.SerializationSolutionSingleton@234bef66
		deserialization object : javastudy.designpattern.singleton.brokepattern.serializationsolution.SerializationSolutionSingleton@234bef66
		 */
	}
}
