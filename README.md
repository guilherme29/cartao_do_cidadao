# cartao_do_cidadao
## General info
This program is a small utility to read and write on the portuguese identity card. This was done for a related class assignment.
The CardIO utility let's you easily:
* Get the name and card code
* The phograph in png format
* The owner's address, provided you run it with the 'address pin' code (there are multiple pins, this one by default is 0000)
* Read and write to the card notes (1Kb size max)

The repo also includes a small script that, provided you already wrote an ssh key to the card (can have 1Kb max size), automatically reads from the card said key and ssh connects you to a given address.

## How to use
```bash
# to get general info about the owner
java CardIO info

# to get the png photo in the card
java CardIO picture <path to file>

# to get the owner's address
java CardIO address <Address pin> 

# to write file to the card notes (has to be less than 1Kb!)
# you will need to insert the authentication pin twice after running
java CardIO write <path to file> 

# read notes from card
java CardIO read <Authentication pin>

# to read ssh key from the card notes and automatically do ssh to a given address
./ssh_authenticate.sh <Authentication pin> <user@localhost>

```

## Extra info
Both 'pt' and 'pteidlib' are directories imported from the cartão de cidadão SDK. If you want the latest version you should get it from there, I only included it in this repository just so it's ready to go. You can get it [here](https://github.com/amagovpt/autenticacao.gov).

The program may crash if for some reason the drivers are not adequate. Usually running it again, turning off and on again, etc can make it work. The program was made to only read/write one card at a time. It will not work for multiple cards at once.

