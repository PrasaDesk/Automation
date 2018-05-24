import socket
import sys
import json
import RPi.GPIO as GPIO

host="192.168.1.101"
port=15000

server_socket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

server_socket.bind((host,port))

server_socket.listen(10)

GPIO.setmode(GPIO.BOARD)
GPIO.setup(7,GPIO.OUT)
GPIO.setup(11,GPIO.OUT)

client,addr=server_socket.accept()
while True:
	recv_msg = client.recv(1024)
	recv_data=recv_msg.decode('utf-8')

	if recv_data=="exit":
		client.close
		GPIO.cleanup()
		break

	elif recv_data=="on 1":
		GPIO.output(7,False)
		print("relay 1 on")


	elif recv_data=="off 1":	
		GPIO.output(7,True)
		print("relay 1 off")


	elif recv_data=="on 2":
		GPIO.output(11,False)
		print("relay 2 on")


	elif recv_data=="off 2":
		GPIO.output(11,True)
		print("relay 2 off")


print("Client send:- "+recv_data)

