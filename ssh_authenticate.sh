# purpose of this script:
# use CardIO to write a ssh private key to the card (not included in this script)
# then use this script to read said key and automatically do ssh,
# assuming the target machine already has your public key in it's authorized_keys file


echo "first argument is the pin, second argument is where you want to ssh (user@localhost)"

PIN=$1
ADDRESS=$2

javac CardIO.java

java CardIO read $PIN > key

chmod 600 key

ssh -i key $ADDRESS

rm key
