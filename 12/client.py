
import socket
import random_number_generator				
#importing file containing PRNG code

client_key=random_number_generator.prng()		
#generation of random number using PRNG Function
print "Client's Random Number (Y):",client_key

#Generating Key K2 for Diffy-Hellman Key Exchange
shared_base=2583
shared_prime=89827481
K2 = (shared_base ** client_key) % shared_prime
print "k2=",K2

#Establishing Connection with server
host = socket.gethostname() 
port = 12001
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.connect((host, port)) 

s.sendall(str(K2))					#sending K2 to server
data = s.recv(1024)					
#recieving K1  from server in 'data'
print "K1 as recieved from Server:",data

Mkey= (int(data) ** client_key) % shared_prime		
#computing common key using K2 & K1 
print "Common Key generated using K1 & K2:",Mkey


########CODE BELOW IS JUST TO DEMONSTRATE CRYPTOGRAPHY WITH EXAMPLE#########
###INPUT TEXT IS TAKEN FROM CLIENT, THEN ITS ENCRYPTED AND SENT TO SERVER###

message = raw_input("Enter the message to be sent: ")

#encoding message using MKey
enc_msg = []						
#list is used to save message
for i in range (0,len(message)):
	enc_msg.append(str(ord(message[i]) + Mkey))	
#converting string to ascii then encoding it and again converting it #string.
							
#ORD converts datatype to ascii
							
#STR converts datatype to string
print "Encoded Message sent to server=",(enc_msg)	
#encoded message is a list of strings.

s.sendall(" ".join(enc_msg))				
#sending message to server in form of "LIST of Strings".

s.close()						#connection closecd 
