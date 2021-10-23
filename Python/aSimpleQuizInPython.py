print('Welcome to Quiz')
answer=input('Ready to play Quiz ? (y/n) :')
score=0
total_ques=3
 
if answer.lower()=='y':
    answer=input('Question 1: What is your Favourite programming language?')
    if answer.lower()=='python':
        score += 1
        print('correct')
    else:
        print('Wrong Answer :(')
 
 
    answer=input('Question 2: Are you a professional in Python? ')
    if answer.lower()=='y':
        score += 1
        print('correct')
    else:
        print('Wrong Answer :(')
 
    answer=input('Question 3: What is the name of your favourite website for learning Python?')
    if answer.lower()=='Python docs':
        score += 1
        print('correct')
    else:
        print('Wrong Answer :(')
 
print('Thanks to play this quiz game, you scored',score,".")
mark=(score/total_ques)*100
print('Total Points:',mark)
print('Keep playing!')