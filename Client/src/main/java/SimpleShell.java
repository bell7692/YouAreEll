import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleShell {



    public SimpleShell() throws UnirestException {
    }


    public static void prettyPrint(String output) throws IOException {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll();
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        //ProcessBuilder class used to help create operating system process
        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //REGEX
        String post = "\\bPOST\\b.\\w\\w [a-z0-9_-]{3,16}$";
        Pattern postPattern = Pattern.compile(post);
        Matcher postMatcher = postPattern.matcher(post);
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            if(commandLine.equals("help")){
                System.out.println("history\t\t\tids\t\t\tmessages\nmessage-log\t\tPOST-id\t\tPOST-message\n");
                continue;
            }
            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids")) {
                    String results = webber.get_ids();
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                // messages
                if (list.contains("messages")) {
                    String results = webber.get_messages();
                    SimpleShell.prettyPrint(results);
                    continue;
                }
                // you need to add a bunch more.
                if(list.contains("message-log")){
                    String results = webber.messageParse();
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                if (list.contains("POST-id")) {
                    System.out.println("What is your name?");
                    String name = console.readLine();;
                    System.out.println("What is your GitHub User ID?");
                    String gitID = console.readLine();;
                    webber.postId(name, gitID);
                    continue;

                }
                if(list.contains("POST-message")){
                    System.out.println("What is your GitHub User Id?");
                    String fromGitID = console.readLine();;
                    System.out.println("Who are you sending the message to?");
                    String toGitID = console.readLine();;
                    System.out.println("What is your message?");
                    String message = console.readLine();;
                    webber.postMessage(fromGitID, toGitID, message);
                    continue;
                }


                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                //Process class is an executing program
                //Used to perform input, output, waiting for the process to complete,
                //checking exit status of the pocess and destroying process
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

//            ProcessBuilder pb1 = new ProcessBuilder();
//            pb1.start();
//            OutputStream outputStream = new OutputStream() {
//                @Override
//                public void write(int b) throws IOException {
//
//                }
//            };




        }


    }

}