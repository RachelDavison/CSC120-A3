import java.util.Scanner;
class Conversation implements ConversationRequirements {

  // Attributes 
  String transcript = "";

  /**
   * Constructor 
   */
  Conversation() {
    this.transcript = "";
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    System.out.println("How many rounds?");
    int rounds = input.nextInt();
    System.out.println("Hi there! What's on your mind?");
    this.transcript += "Hi there! What's on your mind?\n";
    for (int i = 0; i < rounds; i++) {
      String userInput = input.nextLine();
      this.transcript += userInput + "\n";
      String response = respond(userInput);
      System.out.println(response);
      this.transcript += response + "\n";
    }
    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println(this.transcript);
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String response = "";
    boolean mirror = false;
    if (inputString.contains("I ")){
      response = inputString.replace("I ", "You ");
      mirror = true;
    }
    if (inputString.contains(" me ")){
      response = inputString.replace(" me ", " you ");
      mirror = true;
    }
    if (inputString.contains(" my ")){
      response = inputString.replace(" my ", " your ");
      mirror = true;
    }
    if (inputString.contains(" am ")){
      response = inputString.replace(" am ", " are ");
      mirror = true;
    }
    if (inputString.contains("Me ")){
      response = inputString.replace("Me ", "You ");
      mirror = true;
    }
    if (inputString.contains("My ")){
      response = inputString.replace("My ", "Your ");
      mirror = true;
    }
    if (inputString.contains(" you ")){
      response = inputString.replace(" you ", " I ");
      mirror = true;
    }
    if (inputString.contains(" me,")){
      response = inputString.replace(" me, ", " you,");
      mirror = true;
    }
    if (inputString.contains("You ")){
      response = inputString.replace("You ", "I ");
      mirror = true;
    }
    if (inputString.contains(" your ")){
      response = inputString.replace(" your ", " my ");
      mirror = true;
    }
    if (inputString.contains("Your ")){
      response = inputString.replace("Your ", "My ");
      mirror = true;
    }
    if (mirror){
      if (response.contains(".")){
        response = response.replace(".", "?");
      }
      if (response.contains("!")){
        response = response.replace("!", "?");
      }
      if (!response.contains("?")){
        response += "?";
      }
    }
    else{
      String[] cannedResponses = {
        "Interesting, tell me more.",
        "Hmmm.",
        "Do you really think so?",
        "You don't say.",
        "Oh, I see.",
        "That's cool."
      };
      int randomIndex = (int)(Math.random() * 6);
      response = cannedResponses[randomIndex];
    }
    return response;
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
