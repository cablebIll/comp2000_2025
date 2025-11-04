Assignment 2

In this assignment you are demonstrating:

    your java proficiency
    your understanding of design patterns
    your understanding of lambdas and streams

Pay close attention to the rubric, it explains what you will be given marks for and is an important part of this assignment specification.
Your task

Your task is to add some new functionality to the game from your week 11 classwork, and optionally your assignment 1 changes. The functionality must be based upon a stream of data that will be made available to you via a server (HTTP endpoint). You can choose what that functionality is but you will also have to explain why you did that and how it demonstrates intelligent use of lambdas and streams. You should additionally implement design patterns where appropriate.

Just as for assignment 1, the grid is the basis for a game of some sort, use your imagination to think where you might like the game to go as your inspiration. As you think of ideas, keep in mind how you would code them up because you need to come up with something that shows off design patterns, lambdas and streams. You should reject ideas that are most easily coded without needing any lambdas or streams. The HTTP server will be pushing weather information to your game. You should use this "real time" feed of weather to affect the way the game plays. To give you some ideas to start from, you could consider:

    If you followed our suggestion for assignment 1 to make changes so that Cell can have multiple forms.
        Some examples of this could be different landscapes (grass, water, sand, etc.), or
        Differing attributes (eg. altitude, viscosity, etc.)
        These could be achieved by using inheritance, however a more flexible approach could be achieved using one of the design patterns we have studied.
    A simple approach would be to just decorate the playing area with indications of the weather feed, or
    It could be used as a basis for behaviours such as flooding, wind storms, or heat waves.
    Anything else that think would both progress the game design and make use of design patterns, lambdas, streams, and also makes use of the data coming from the server.

The HTTP Server

The server is available at http://13.238.167.130/weather. Connecting to it will allow you to receive the weather feed. The weather is defined by four attributes: rainfall, windX, windY, and temperature. Each of these is a floating-point number ranged between 0.0 and 1.0. How you interpret these values is up to you, but you will need to explain how you've interpreted it in your README.md

The server will send a line with the following format for each: timestamp attribute x-coordinate y-coordinate value, each record is on a separate line and each field is separated by a single space character.
For example

1760664486 rain 7 9 0.44
1760664486 windx 7 9 0.50
1760664486 windy 7 9 0.58
1760664486 temp 7 9 0.53
1760664486 rain 8 -7 0.38
1760664486 windx 8 -7 0.50
1760664486 windy 8 -7 0.54
1760664486 temp 8 -7 0.54

The origin of the coordinate space (0, 0) is at the centre of the game area. Note that you may find some attributes that fall outside your grid area, you should ensure your program can deal with these appropriately. The timestamp field is a long value that contains the number of seconds since the Unix Epoch.
Submission

You will submit a zip of all of your source code plus a README.md file that:

    adds some new functionality to the grid from week 11 and optionally the modifications you made for assignment 1.
    includes a single README.md file which
        describes which design patterns you have used, and how they contributed to a good design for your program,
        discusses what stream operations your program performs, and how you've used lambdas to implement them,
        explains how you've interpreted the values from the server,

As you can see in the rubric, a proportion of your grades is dependent on the quality of the explanation you have provided in your readme. Your readme must also contain clear instructions to your marker of how to compile and run your program. Your marker will have both Java 11 and Java 21 available but no other Java versions.

You are free to use any and everything from your team's work on this task. You may not use other team's work directly or solicit for solutions. These remain contraventions of academic integrity. The work of the team belongs to all team members, so they can submit that without contravening academic integrity. However, this is your own submission. You can, and should, improve upon your team's work as much as possible. We have included a "similarity/creativity" score in the rubric so we can reward solutions that go beyond what was done in class.

You are also free to share ideas with other students of what to add and how that might work with inheritance, but you may not share code or readme files - this is an individual submission.

Please refer to the associated rubric for the grading criteria.