
#Title: Write a program to generate a pseudorandom number generator for 	generating the long-term private key and the ephemeral keys used for 	each signing based on SHA-1 using Python/Java/C++. Disregard the use 	of existing pseudorandom number generators available.
-----------------------------------------------------------------
###------------------------Server.py-------------------------
import socket
import random_number_generator		
		
#importing file containing	PRNG code

server_key=random_number_generator.prng()	

#generation of random number using PRNG Function

print "Server's Random Number (X) :",server_key

#Generating Key K1 for Diffy-Hellman Key Exchange
shared_base=2583
shared_prime=89827481
K1 = (shared_base ** server_key) % shared_prime
print "K1=",K1

#Establishing Server
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM,socket.IPPROTO_TCP)
host = socket.gethostname() 
port = 12001
s.bind((host,port))
s.listen(3)              
new_s, addr=s.accept()


msg=new_s.recv(200)					
#Recieving K2 from client in 'msg'
print "K2 as recieved from client:",msg

new_s.send(str(K1))					#Sending K1 to Client


Mkey = (int(msg) ** server_key) % shared_prime		
#computing common key using K2 & K1
print "Common Key generated using K1 & K2:",Mkey


###########CODE BELOW IS JUST TO DEMONSTRATE CRYPTOGRAPHY WITH EXAMPLE##########
###ENCODED MESSAGE IS RECIEVED FROM CLIENT, THEN ITS DECRYPTED AND DISPLAYED####

enc_msg=new_s.recv(1024)				
#Recieving Encoded Message from CLEINT
x = [int(i) for i in enc_msg.split()]			
#converting Recieved String into List for decoding
							
#split() function is used for this purpose
print "Recieved Encoded Message: ",x

#Decoding Encrypted Message
dec_msg =[]
for j in range (0,len(x)):
	dec_msg.append(chr(x[j] - Mkey))		
#CHR converts datatype to character
							
#append adds next value at the end

print "Decrypted Message=","".join(dec_msg)		
#printing list as string

s.close()						#close connection
new_s.close()						#close server


###----------------------Client.py----------------------------

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

s.close()						#connection close

#----------------------------------------------------------
Output
---------------Server output---------------------
prj19@prj19:~$ cd Desktop
prj19@prj19:~/Desktop$ python server.py 
Server's Random Number (X) : 127994
K1= 54739702
K2 as recieved from client: 47500230
Common Key generated using K1 & K2: 47513449
Recieved Encoded Message:  [47513553, 47513550, 47513557, 47513557, 47513560]
Decrypted Message= hello
prj19@prj19:~/Desktop$

---------------client output----------------------
prj19@prj19:~$ cd Desktop
prj19@prj19:~/Desktop$ python client.py 
Client's Random Number (Y): 103952
k2= 47500230
K1 as recieved from Server: 54739702
Common Key generated using K1 & K2: 47513449
Enter the message to be sent: hello
Encoded Message sent to server= ['47513553', '47513550', '47513557', '47513557', '47513560']
prj19@prj19:~/Desktop$ 




