public class Bayesian_Network 
{
	Node B, E, A, J, M;
	
	public Bayesian_Network() 
	{
		B = new Node('B', Node.Node_location.INITIAL_NODE);
		E = new Node('E', Node.Node_location.INITIAL_NODE);
		A = new Node('A', Node.Node_location.INTERMEDIATE_NODE);
		J = new Node('J', Node.Node_location.END_NODE);
		M = new Node('M', Node.Node_location.END_NODE);
	}
	public void process_Args(String[] args) 
	{
		boolean b = false;
		int count=0;
		for(count=0;count < args.length;count++) {
			if (args[count].equalsIgnoreCase("given")) {
				b = true;
			}
			else if (args[count].equalsIgnoreCase("Bf") || args[count].equalsIgnoreCase("Ef") || args[count].equalsIgnoreCase("Af") || args[count].equalsIgnoreCase("Jf") || args[count].equalsIgnoreCase("Mf") || args[count].equalsIgnoreCase("Bt") || args[count].equalsIgnoreCase("Et") || args[count].equalsIgnoreCase("At") || args[count].equalsIgnoreCase("Jt") || args[count].equalsIgnoreCase("Mt")) {
				false_statements(args, b, count);
				true_statements(args, b, count);
			}
			else {
				System.out.println("Wrong Input. Try Again");
				System.exit(0);
			}
		}
	}
	public void false_statements(String[] args, boolean b, int count){
		if (args[count].equalsIgnoreCase("Bf"))
			set_value_false(this.B, b);
		else if (args[count].equalsIgnoreCase("Ef"))
			set_value_false(this.E, b);
		else if (args[count].equalsIgnoreCase("Af"))
			set_value_false(this.A, b);
		else if (args[count].equalsIgnoreCase("Jf"))
			set_value_false(this.J, b);
		else if (args[count].equalsIgnoreCase("Mf"))
			set_value_false(this.M, b);

	}
	public void true_statements(String[] args, boolean b, int count){
		if (args[count].equalsIgnoreCase("Bt"))
			set_value_true(this.B, b);
		else if (args[count].equalsIgnoreCase("Et"))
			set_value_true(this.E, b);
		else if (args[count].equalsIgnoreCase("At"))
			set_value_true(this.A, b);
		else if (args[count].equalsIgnoreCase("Jt"))
			set_value_true(this.J, b);
		else if (args[count].equalsIgnoreCase("Mt"))
			set_value_true(this.M, b);
	}

	public void set_value_false(Node node, boolean b)
	{
		node.startvalue = 1;
		if (b == true)
			node.begining = 1;
	}

	
	public void set_value_true(Node node, boolean b)
	{
		node.endvalue = 0;
		if (b == true)
			node.ending = 0;
	}
	
	
	
	public static double computeProbability (Node B, Node E, Node A, Node J, Node M)
	{
		double d2 = 0;
		for (int i = B.startvalue; i <= B.endvalue; i++)
			for (int k = E.startvalue; k <= E.endvalue; k++)
				for (int l = A.startvalue; l <= A.endvalue; l++)
					for (int j = J.startvalue; j <= J.endvalue; j++)
						for (int p = M.startvalue; p <= M.endvalue; p++)
							d2 += (Node.computeProbability(B, i, -1, -1) * Node.computeProbability(E, k, -1, -1) * Node.computeProbability(A, l, i, k) * Node.computeProbability(J, j, l, -1) * Node.computeProbability(M, p, l, -1));

		double d1 = 0;
		for (int i = B.begining; i <= B.ending; i++)
			for (int k = E.begining; k <= E.ending; k++)
				for (int l = A.begining; l <= A.ending; l++)
					for (int j = J.begining; j <= J.ending; j++)
						for (int m = M.begining; m <= M.ending; m++)
							d1 += (Node.computeProbability(B, i, -1, -1) * Node.computeProbability(E, k, -1, -1) * Node.computeProbability(A, l, i, k) * Node.computeProbability(J, j, l, -1) * Node.computeProbability(M, m, l, -1));


		return (d2/d1);
	}

	public static void main(String[] args) 
	{
		if (args.length == 0) 
		{
			System.out.println("I/P arguments:");
			System.exit(0);
		}
		if (args.length > 6) 
		{
			System.out.println("Insert I/P arguments [1 2 3 4 5]");
			System.exit(0);
		}
		Bayesian_Network b_net = new Bayesian_Network();
		b_net.process_Args(args);
		System.out.println(" \n\n Joint Probability: " + computeProbability(b_net.B,b_net.E, b_net.A, b_net.J, b_net.M));
			
	}
}

class Node
{
	static final double PA_BT_EF = 0.94;
	static final double PA_BF_ET = 0.29;
	static final double PB = 0.001;
	static final double PE = 0.002;
	static final double PJ_AT = 0.90;
	static final double PJ_AF = 0.05;
	static final double PM_AT = 0.70;
	static final double PM_AF = 0.01;
	static final double PA_BT_ET = 0.95;
	static final double PA_BF_EF = 0.001;
	char c;
	Node_location pos;
	int startvalue;
	int endvalue;
	int begining, ending;
	public enum Node_location
	{
		INITIAL_NODE,
		INTERMEDIATE_NODE,
		END_NODE,
		NONE
	}

	public Node(char ch, Node_location pos2)
	{
		startvalue = 0;
		endvalue = 1;
		begining = 0;
		ending = 1;
		c = ch;
		pos = pos2;

	}

	public static double computeProbability(Node temp, int f, int b1, int e1)
	{
		double probabilityvalue= 0;
		if (temp.pos == Node_location.INITIAL_NODE)
		{
			if (temp.c == 'B')
			{
				if (f == 1)
					probabilityvalue = PB;
				else
					probabilityvalue = 1 - PB;
			}
			else
			{
				if (f == 1)
					probabilityvalue = PE;
				else
					probabilityvalue = 1 - PE;
			}
		}
		else if (temp.pos == Node_location.END_NODE)
		{
			if (temp.c == 'J')
			{
				if (b1 == 1)
				{
					if (f == 1)
						probabilityvalue = PJ_AT;
					else
						probabilityvalue = 1 - PJ_AT;
				}
				else
				{
					if (f == 1)
						probabilityvalue = PJ_AF;
					else
						probabilityvalue = 1 - PJ_AF;
				}
			}
			else if (temp.c == 'M')
			{
				if (b1 == 1)
				{
					if (f == 1)
						probabilityvalue = PM_AT;
					else
						probabilityvalue = 1 - PM_AT;
				}
				else
				{
					if (f == 1)
						probabilityvalue = PM_AF;
					else
						probabilityvalue = 1 - PM_AF;
				}
			}
		}
		else if (temp.pos == Node_location.INTERMEDIATE_NODE)
		{
			if ((b1 == 1) && (e1 == 1))
			{
				if (f == 1)
					probabilityvalue = PA_BT_ET;
				else
					probabilityvalue = 1 - PA_BT_ET;
			}
			else if ((b1 == 0) && (e1 == 1))
			{
				if (f == 1)
					probabilityvalue = PA_BF_ET;
				else
					probabilityvalue = 1 - PA_BF_ET;
			}
			else if ((b1 == 1) && (e1 == 0))
			{
				if (f == 1)
					probabilityvalue = PA_BT_EF;
				else
					probabilityvalue = 1 - PA_BT_EF;
			}

			else if ((b1 == 0) && (e1 == 0))
			{
				if (f == 1)
					probabilityvalue = PA_BF_EF;
				else
					probabilityvalue = 1 - PA_BF_EF;
			}
		}
		return probabilityvalue;
	}
}