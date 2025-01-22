# java-deb-installer
A desktop Java program for installing .deb packages using a GUI on Debian-based distributions.

This program was designed because Ubuntu changed its installation system to snap, disabling the double-click installation method for .deb packages. For an experimented Linux user, this installation is simple, because you can use "sudo dpkg -i name-of-your-package.deb". But for newbies, a double click graphical installation could be util.

<h2>Simple download (.jar)</h2>

Just download the .jar file <b>Deb-Installer.jar</b>, and move it to a directory easy to remember (example, Desktop or Documents). Now, you could have permission issues with the jar: open a terminal (right-click) in the directory where you saved the .jar, and type the following command:

<b style="font-family: Courier, monospace;">ls -l | grep Deb-Installer.jar</b>

You should see something like this:

<b style="font-family: Courier, monospace;">-rw-rw-r-- 1 your_user your_user    3813 ene 18 17:19 Deb-Installer.jar</b>

To add execution permissions, just run this command:

<b style="font-family: Courier, monospace;">chmod +111 Deb-Installer.jar</b>

If you repeat the list (<b>ls -l</b>)command, now your Deb-Installer should look like this:

<b style="font-family: Courier, monospace;">-rwxrwxr-x 1 your_user your_user    3813 ene 18 17:19 Deb-Installer.jar</b>

If there are <i>x</i>'s at the output, then it's done.


<h2>Full download (optional)</h2>

If you want remake the .jar in your computer, just download the .java files and the manifest.txt. Then, open a terminal in the directory where you downloaded the files and run:

<b style="font-family: Courier, monospace;">javac *.java</b>

Now, package the .class files and the manifest.txt in a .jar:

<b style="font-family: Courier, monospace;">jar cvfm Deb-Installer.jar manifest.txt *.class</b>

Finally, you must set the execution permissions for the .jar, as in the <b>Simple Download</b> process.


<h2>Installing .deb's</h2>

Now, you can install a .deb package using a simple GUI. Just double click the <b>Deb-Installer.jar</b> file, and follow the assistant.
