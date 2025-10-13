import java.util.Scanner;
import java.util.ArrayList;
class Conversation implements ConversationRequirements {

  // Attributes 
  private ArrayList<String> transcript;
  private String[] cannedResponses; 

  /**
   * Constructor 
   */
  public Conversation() {
    this.transcript = new ArrayList<String>();
    this.transcript.add("\nTRANSCRIPT:");
    this.cannedResponses = new String[] {
      "Interesting, tell me more.",
      "Hmmm.",
      "Do you really think so?",
      "You don't say.",
      "Oh, I see.",
      "That's cool.",
      "Fascinating.",
      "Wow!",
      "Could you elaborate on that?",
      "I understand.",
    };
  }

  /**
   * Starts and runs the conversation with the user, and adds it to the transcript
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    System.out.println("How many rounds?");
    int rounds = input.nextInt();
    input.nextLine(); // consume the newline character
    System.out.println("Hi there! What's on your mind?");
    this.transcript.add("Hi there! What's on your mind?");
    for (int i = 0; i < rounds; i++) {
      String userInput = input.nextLine();
      this.transcript.add(userInput);
      String response = respond(userInput);
      System.out.println(response);
      this.transcript.add(response);
    }
    System.out.println("See ya!");
    this.transcript.add("See ya!");
    input.close();
    this.printTranscript();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    for (int i = 0; i < this.transcript.size(); i++){
      System.out.println(this.transcript.get(i));
    }
  }

  /**
   * Checks if the substring is a word in the input string
   * @param inputString the entire input string 
   * @param i the starting index of the substring
   * @param j the ending index of the substring
   * @return true if the substring is a whole word based on spaces and punctuation, false otherwise
   */
  private boolean isAWord(String inputString, int i, int j){
    if (i == 0 || inputString.substring(i-1, i).equals(" ") || inputString.substring(i-2, i).equals(". ") || inputString.substring(i-2, i).equals(", ") || inputString.substring(i-2, i).equals("! ") || inputString.substring(i-2, i).equals("? ")){
      if (j == inputString.length() || inputString.substring(j, j+1).equals(" ") || inputString.substring(j, j+1).equals(".") || inputString.substring(j, j+1).equals(",") || inputString.substring(j, j+1).equals("!") || inputString.substring(j, j+1).equals("?")){
        return true;
      }
    }
    return false;
  }

  /** 
   * Checks if the substring is at the beginning of a sentence in the input string
   * @param inputString the entire input string 
   * @param i the starting index of the substring
   * @param j the ending index of the substring
   * @return true if the substring is at the beginning of a sentence, false otherwise
   */
  private boolean afterPunctuation(String inputString, int i, int j){
    return (inputString.substring(i-2, i).equals(". ") || inputString.substring(i-2, i).equals("! ") || inputString.substring(i-2, i).equals("? "));
  }

  /**
   * Gives appropriate response (mirrored or canned) to a string
   * @param inputString the string being responded to
   * @return mirrored or canned response to the input string  
   */
  public String respond(String inputString) {
    boolean mirror = false;
    for (int i = 0; i < inputString.length(); i++){
      for (int j = i; j <= inputString.length(); j++){
        if ((inputString.substring(i, j).equalsIgnoreCase("I") || inputString.substring (i, j).equalsIgnoreCase("me")) && isAWord(inputString, i, j)){
          // Check for keywords I and me, change them to you
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            // if keyword is at the beginning of the entire string, capitalize the response
            inputString = "You" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){ 
            // if keyword is at the beginning of a sentence, capitalize the response
            inputString = inputString.substring(0,i) + "You" + inputString.substring(j);
          } else { 
            // if it's not at the beginning of a sentence, don't capitalize the response
            inputString = inputString.substring(0,i) + "you" + inputString.substring(j);
          }
          mirror = true; // this is used later to determine if a question mark should be added, as they are only added to mirrored responses
          i = i + 3; // this skips past the mirrorered word so it isn't remirrored back to its original state
          j = i; //this makes sure j is always ahead of i
        } else if (inputString.substring(i,j).equalsIgnoreCase("am") && isAWord(inputString, i, j)){ 
          // Check for keyword am, change to are
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            inputString = "Are" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){
            inputString = inputString.substring(0,i) + "Are" + inputString.substring(j);
          } else {
            inputString = inputString.substring(0,i) + "are" + inputString.substring(j);
          }
          mirror = true; 
          i = i + 3;
          j = i;
        } else if (inputString.substring(i, j).equalsIgnoreCase("my") && isAWord(inputString, i, j)){ 
          // Check for keyword my, change to your
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            inputString = "Your" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){
            inputString = inputString.substring(0,i) + "Your" + inputString.substring(j);
          } else {
            inputString = inputString.substring(0,i) + "your" + inputString.substring(j);
          }
          mirror = true;
          i = i + 4;
          j = i;
        } else if (inputString.substring(i, j).equalsIgnoreCase("you") && isAWord(inputString, i, j)){ 
          // Check for keyword you, change to I or me depending on context
          if (i == 0){
            inputString = "I" + inputString.substring(j);
          } else if ((i > 3 && inputString.substring(i-3, i).equals("at ")) || (i > 4 && inputString.substring(i-4, i).equals("for ")) || (i > 3 && inputString.substring(i-3, i).equalsIgnoreCase("to ")) || (i > 8 && inputString.substring(i-8, i).equalsIgnoreCase("towards ")) || (i > 5 && inputString.substring(i-5, i).equalsIgnoreCase("with ")) || (i > 8 && inputString.substring(i-8, i).equalsIgnoreCase("without "))){ // Check if "you" is preceded by "to ", "at ", "towards ", "with ", "for ", "without ", change to "me"
            inputString = inputString.substring(0,i) + "me" + inputString.substring(j);
          } else{
            inputString = inputString.substring(0,i) + "I" + inputString.substring(j);
          }
          mirror = true;
          i = i + 1;
          j = i;
        } else if (inputString.substring(i, j).equalsIgnoreCase("are") && isAWord(inputString, i, j)){ 
          // Check for keyword are, change to am
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            inputString = "Am" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){
            inputString = inputString.substring(0,i) + "Am" + inputString.substring(j);
          } else {
            inputString = inputString.substring(0,i) + "am" + inputString.substring(j);
          }
          mirror = true;
          i = i + 2;
          j = i;
        } else if (inputString.substring(i, j).equalsIgnoreCase("your") && isAWord(inputString, i, j)){ 
          // Check for keyword your, change to my
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            inputString = "My" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){
            inputString = inputString.substring(0,i) + "My" + inputString.substring(j);
          } else {
            inputString = inputString.substring(0,i) + "my" + inputString.substring(j);
          }
          mirror = true;
          i = i + 2;
          j = i;
        } else if (inputString.substring(i,j).equalsIgnoreCase("I'm") && isAWord(inputString, i, j)){ 
          // Check for keyword I'm, change to You're
          if (i == 0 || (i == 1 && inputString.substring(0,1).equals(" "))){
            inputString = "You're" + inputString.substring(j);
          } else if (afterPunctuation(inputString, i, j)){
            inputString = inputString.substring(0,i) + "You're" + inputString.substring(j);
          } else{
            inputString = inputString.substring(0,i) + "you're" + inputString.substring(j);
          }
          mirror = true;
          i = i + 6;
          j = i;
        } else if (inputString.substring(i,j).equalsIgnoreCase("you're") && isAWord(inputString, i, j)){
          // Check for keyword you're, change to I'm
          if (i == 0){
            inputString = "I'm" + inputString.substring(j);
          } else {
            inputString = inputString.substring(0,i) + "I'm" + inputString.substring(j);
          }
          mirror = true;
          i = i + 3;
          j = i;
        }      
      }
    }
    if (mirror){
      // If a mirroring was done, ensure the response ends in a question mark, and any sentences within it end in a question mark
      if (inputString.contains(".")){
        inputString = inputString.replace(".", "?");
      }
      if (inputString.contains("!")){
        inputString = inputString.replace("!", "?");
      }
      if (!inputString.contains("?")){
        inputString += "?";
      }
    } else {
      // If no mirroring was done, give a random canned response
      int randomIndex = (int)(Math.random() * this.cannedResponses.length);
      inputString = this.cannedResponses[randomIndex];
    }
    return inputString;
  }

  public static void main(String[] arguments){

    Conversation myConversation = new Conversation();
    myConversation.chat();
  }
}