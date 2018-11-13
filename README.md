# SeriesProgram
Program for league program that fixes rounds with given rules

For example we have 6 teams that play double series which means that each team plays against each other twice - once,
home and visiting.

Program is supposed to work with any amount of teams and rounds. Amount of rounds is calculated with (n-1)*m,
where n = amount of teams and m = amount times teams go against eachother. 
If n is not even number it's rounded up for example 15 rounds to 16.

All games are first randomized for rounds and then fixed.
