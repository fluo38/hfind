README

Just a small Java program to play with GitHub! A la command-line...

hfind is just like find, except that it works level by level!

So, the following options make sense:
  --min-depth n : anything that's deep enough
  --max-depth m : don't go deeper than m
  --level l     : only one level
  
Other options:
  --display-files / --do-not-display-files
  --display-directories / --do-not-display-directories
  
Examples: Guess what these do:
  hfind --display-directories --do-not-display-files --level 1 /Users
  hfind --display-directories --do-not-display-files $HOME
  