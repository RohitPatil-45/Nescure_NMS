package npm.admin.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;

public class TestSSH {
	
	public static void main(String[] args) {
		
		TestSSH sh = new TestSSH();
		System.out.println(sh.checkSSH("192.168.10.1", "admin", "admin", "show version"));
		
	}
	
	public String checkSSH(String ipAddress, String username, String password, String command) {
		System.out.println("inside Read SSH");
		String line2 = "";
//	 String command = "show system snmp sysinfo";

		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		String datetime = String.valueOf(timestampNow.getTime());
		System.out.println("Data:" + ipAddress + ":" + username + ":" + password + ":" + command + ":" + datetime);

		// Maintain Log
		File sampleFile = null;
		try {
			sampleFile = new File("C:\\ServiceProvisioning\\" + ipAddress + "_" + datetime + ".txt");
			if (sampleFile.exists()) {
				if (sampleFile.delete()) {
					System.out.println(sampleFile.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			} else {
				sampleFile.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		StringBuilder builder = new StringBuilder("Device OP:");

		// Start SSH Command
		JSch jsch = null;
		InputStream input = null;
		Channel channel = null;
		com.jcraft.jsch.Session session = null;
		try {
			jsch = new JSch();
			session = jsch.getSession(username, ipAddress, 22);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			channel = session.openChannel("shell");
			//Thread.sleep(10000);
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = null;
			ps = new PrintStream(ops, true);
			channel.connect();
			//Thread.sleep(10000);
			input = channel.getInputStream();
			//System.out.println("input = "+input.);
			Thread.sleep(2000);
			ps.println(command);
			Thread.sleep(2000);
			ps.println("exit");
			ps.close();
			int SIZE = 1024;
			byte[] tmp = null;
			tmp = new byte[SIZE];
			System.out.println("tmp = "+tmp.length);
			while (true) {
				System.out.println("in while size is = "+input.available());
				while (input.available() > 0) {
					int i = input.read(tmp, 0, 1024);
					if (i < 0) {
						break;
					}

					String a = null;
					a = new String(tmp, 0, i);
			 System.out.print("OP: " + a + ":End");
					builder.append(a);
					try {
						PrintWriter out = null;
						out = new PrintWriter(new FileWriter(sampleFile, true));
						out.append(a);

						out.close();
					} catch (IOException e) {
						System.out.println("COULD NOT LOG!!");
					}

				}
				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}

			}

		} catch (Exception ee) {
			System.out.println("errrrrrrrooorrr " + ee);
		} finally {
			try {
				channel.disconnect();
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
			try {
				session.disconnect();
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
			try {
				input.close();
			} catch (Exception e) {
				// System.out.println("Exception:" + e);
			}

		}
	
		return builder.toString();

	}

}
