
import nltk
# Define rules and responses
rules = {
    "What is your name?": "My name is ChatBot.",
    "How are you?": "I'm good, thank you!",
    "What time is it?": "It is currently 12:00 PM.",
    "What is the weather like today?": "The weather is sunny today.",
    "Goodbye!": "Goodbye! Have a nice day!"
}
#Function to get the response based on user input
def get_response(user_input):
    if user_input in rules:
      
        response = rules[user_input]
    else:
        response = "Sorry, I don't have a response for that."

    return response
# Main program loop
while True:
    user_input = input("User: ")

    if user_input.lower() == "bye":
        print("Bot: Goodbye!")
        break

    response = get_response(user_input)
    print("Bot:", response)
