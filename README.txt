
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Programming Language used: JAVA

-class Bayesian_Network
	# void process_Arguments: Processes values taken from user and provides to correct input arguments.
	# void set_value_true: If query is mentioned in end, the value is set to 0.
	# void set_value_false: If query is mentioned in beginning, value is set to 1.
	# false_statements: checks all queries that are false.
	# true_statements: checks all queries that are true.

	
-class Node
	# double computeProbability: Calculates the joint probability.

Bayesian_Network takes 6 arguments.
	- John Calls
	- Burglary
	- Alarm
	- Mary Calls
	- Value for true
	- Value for false


Steps to run code:
	> javac Bayesian_Network.java
	> java Bayesian_Network [input arguments]
	Eg: java Bayesian_Network Jt Af given Bt Ef

