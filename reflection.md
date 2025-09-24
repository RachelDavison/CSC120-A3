Use this file to record your reflection on this assignment.

- Which classmates did you consult with while working on this assignment? Eliana
- Which session(s) of TA / office hours did you attend?
- What are your initial impressions of Java? 
- Can you draw any conclusion about programming in general from the similarities or the differences between the two languages? 
- What worked, what didn't, what advice would you give someone taking this course in the future?

Before working on mirroring, I wrote the basic code that asks for the number of rounds and then loops for that number, and wrote a bit of code that adds a question mark to a statement if that statement has been mirrored.

My first idea was to split the String into words, but I ran into a lot of problems because I didn't know how to use the Dictionary tool. Then I decided to simplify using the contains and replace methods, but I didn't trust that I'd think of all the permutations of mirror words and punctuation. Finally, I decided to iterate through the String using a nested for loop, and create a different method to check if the bit of String I was looking at was a word. Then I made else-if-statements to determine if the word was a word I could mirror and if so, mirror it. This created the problem that the next time I iterated through the String it undid the mirror by mirroring it back to how it was in the first place. To fix this, I added at the end of each else-if an i + (the length of the mirror word) so that it started the next iteration past the word it just changed, and a i + j to keep the j number equal to or past the i number. Finally, when I looked at the rubric I realised the transcript was supposed to be an array rather than a tring, so I rewrote that bit of the code.

I worked with Eliana on this assignment. We met up rather late in our coding, so our solutions don't look much alike, but it was nice to see the Dictionary solution done well, since that was one of my first ideas that I abandoned, and her method of implementing it used a lot less repeated code than my iteration did. We tried to also work with Elle, but she didn't show up when we met up.

I went to the session of office hours at Monday 7:00 PM with Binah.

This wasn't my initial impression of Java, because I took AP Computer Science A in high school, but my impression of Java during this assignment was that it does a lot more things for you than I thought it did. I learned Java without a lot of the methods I'm now discovering, so my problem-solving tends to be very skewed towards iteration through things rather than using methods to do that kind of thing for me. 

I don't know any languages other than Java, so I don't know what possible differences might say about programming in general, but I'd guess that the existence of differences between programming languages shows that programming is something that can be done from many different perspectives and ways of solving problems, and that the variety in languages reflects that variety of perspectives.

To someone taking this course in the future, I might advise them to not give up on a way of doing something so quickly. I solved the problem, and my solution does work, but looking at Eliana's code made me wish that I'd tried the new methods I was unfamiliar with, because solving this problem with a new area of Java would have taught me new things about Java, while solving it the way I'm familiar with, while challenging and interesting, didn't really teach me new things about Java.