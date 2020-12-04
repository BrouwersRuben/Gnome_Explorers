# This is the project for Integration 1.1

=========================================

# Instructions for establishing a connection with the database

https://docs.google.com/document/d/1hRWHRfusqSrP8f5brVXzokqbqwVQPFiR/

## Windows

1. Click Windows icon (or press Windows key)
2. Type "Advanced system settings"
3. Press ENTER
4. Click "Environment Variables"
5. Under the "User variables" click on "New"
6. There are 3 variables that need to be added.

Variable "TNS_ADMIN" with value pointing to the db folder inside the project.
Ex: "D:\Development\ascii-game\db"

Variable "DB_USERNAME" with value "INTEGRATION PROJECT".

Variable "DB_PASSWORD" with value "WildLlamaEntertainment1".

7. After adding the variables, restart IntelliJ if it was open.
8. Run the game. You should get a system message "Connected to the database".

## Linux

1. Open a terminal
2. Edit the rc of your shell (For example ~/.bashrc)
3. There are 3 variables that need to be added.

The values are the same as for Windows.

Example for all 3:
export TNS_ADMIN ="/home/mihnea/Development/University/ascii-game/db"
export DB_USERNAME="INTEGRATION PROJECT"
export DB_PASSWORD="WildLlamaEntertainment1"

4. After adding the variables, log out and back in.
5. Run the game. You should get a system message "Connected to the database".