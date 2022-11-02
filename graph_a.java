class graph_a
{
	public int values[][];
	public int n, k;
	public point points[];
	public way ways[];
	public int infinity = Integer.MAX_VALUE/2;
	public graph_a(int size)
	{
		values = new int[size][size];
		points = new point[size];
		ways = new way[size*(size-1)/2];
		n = 0;
		k = 0;
		int i, j;
		for(i = 0; i < size; i++)
		{
			for(j = 0; j < size; j++)
			{
				values[i][j] = 0;
			}
		}
	}
	public void addPoint(String name)
	{
		points[n] = new point(name, n);
		n++;
	}
	public point stringToPoint(String str)
	{
		int i;
		for(i = 0; i < n; i++)
		{
			if(str.equals(points[i].name) == true)
			{
				return points[i];
			}
		}
		return null;
	}
	public void addWay(String b, String e, int value)
	{
		point begining = stringToPoint(b);
		point end = stringToPoint(e);
		ways[k] = new way(begining, end, value);
		k++;
	}
	public int nextVisit(int p)
	{
		int i;
		for(i = 0; i < n; i++)
		{
			if(values[p][i] > 0 && points[i].visited == false)
			{
				 return i;
			}
		}
		return -1;  
	}
	public stack st = new stack(n);
	public void dfs(String str) 
	{
		st = new stack(n);
		point begining = stringToPoint(str);
		points[begining.number].visited = true;
		System.out.print(points[begining.number].name + " ");
		st.push(begining.number);
		while(!st.empty())
		{
			int i = nextVisit(st.peek());
			if(i == -1)
			{
				st.pop();
			}
			else
			{
				points[i].visited = true;
				System.out.print(points[i].name + " ");
				st.push(i);
			}
		}
        System.out.println();
		int j;
		for(j = 0;j < n; j++)
		{
			points[j].visited = false;
		}
	}
	public queue qu = new queue();
	public void bfs(String str)
	{
		point begining = stringToPoint(str);
		points[begining.number].visited = true;
		System.out.print(points[begining.number].name + " ");
		qu.add(begining.number);
		int tmp, numb;
		while(!qu.empty())
		{
			tmp = qu.delete();
			while((numb = nextVisit(tmp)) != -1)
			{
				System.out.print(points[numb].name + " ");
				points[numb].visited = true;
				qu.add(numb);
			}
		}
        System.out.println();
		int j;
		for(j = 0;j < n; j++)
		{
			points[j].visited = false;
		}
	}
	public void Dijkstra(String str)
	{
		point begining = stringToPoint(str);
		int road[] = new int[n];
		boolean metka[] = new boolean[n];
		int i;
		for(i = 0; i < n; i++)
		{
			road[i] = infinity;
		}
		road[begining.number] = 0;
		while(true)
		{
			int tmp = -1;
			for(i = 0; i < n; i++)
			{
				if((tmp == -1 || road[tmp] > road[i]) && (metka[i] == false) && (road[i] < infinity))
				{
					tmp = i;
				}
			}
			if(tmp == -1)
			{
				break;
			}
			metka[tmp] = true;
			for(i = 0; i < n; i++)
			{
				if(metka[i] == false && values[tmp][i] != 0)
				{
					if((road[tmp] + values[tmp][i]) < road[i])
					{
						road[i] = (road[tmp] + values[tmp][i]);
					}
				}
			}
		}
		for(i = 0; i < n; i++)
		{
			System.out.println(str + "-->" + points[i].name + " = " + road[i]);
		}
	}
	public void Floyd_Warshall()
	{
		int i, j, k;
		int road[][] = new int[n][n];
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				if(i == j)
				{
					road[i][j] = 0;
				}
				else
				{
					road[i][j] = infinity;
				}
			}
		}
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				if(values[i][j] != 0)
				{
					road[i][j] = values[i][j];
				}
			}
		}
		for(k = 0; k < n; k++)
		{
			for(i = 0; i < n; i++) 
			{
				for(j = 0; j < n; j++)
				{
					if((road[k][j] + road[i][k]) < road[i][j])
					{
						road[i][j] = road[k][j] + road[i][k];
					}
				}
			}
		}
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				System.out.print(road[i][j] + ", ");
			}
			System.out.println();
		}
	}
	public void Prim()
	{
		int d[] = new int[n];
        boolean visit[] = new boolean[n];
        int parent[] = new int[n];
        int i, j, tmp, index;
        for (i = 0; i < n; i++) 
        {
            d[i] = infinity;
            visit[i] = false;
            parent[i] = 0;
        }
        d[0] = 0;
        parent[0] = -1;
        for(i = 0; i < n - 1; i++) 
        {
            tmp = infinity;
            index = 0;
            for(j = 0; j < n; j++)
            {
            	if(visit[j] == false && d[j] < tmp)
            	{
            		tmp = d[j];
            		index = j;
            	}
            }
            visit[index] = true;
            for(j = 0; j < n; j++)
            {
                if (values[index][j]>0 && !visit[j] && values[index][j] < d[j]) 
                {
                    d[j] = values[index][j];
                    parent[j] = index;
                }
            }
        }
        System.out.println("(Parent[v], v)"); 
        for(i = 1; i < n; i++)
        {
        	System.out.println("(" + points[parent[i]].name + ", " + points[i].name + ") = " + values[parent[i]][i]);
        }
	}
	public void Kruskal()
	{
		int parent[] = new int[n];
		int rank[] = new int[n];
		way a[] = new way[n];
		way sortedWays[] = new way[k];
		int i;
		for(i = 0; i < k; i++)
		{
			sortedWays[i] = ways[i];
		}
		sort(sortedWays);
		for(i = 0; i < n; i++)
		{
			a[i] = new way();
			parent[i] = i;
			rank[i] = 0;
		}
		int e = 0;
		i = 0;
		while(e < (n - 1))
		{
			way nway = new way();
			nway = sortedWays[i++];
			int x = find(nway.begining.number, parent);
			int y = find(nway.end.number, parent);
			if(x != y)
			{
				a[e++] = nway;
				union(x, y, parent, rank);
			}
		}
	    System.out.println("(Parent[v], v)"); 
		for(i = 0; i < e; i++)
		{
			System.out.println("(" + a[i].begining.name + ", " + a[i].end.name + ") = " + a[i].value);
		}
	}
	public void union(int u, int v, int parent[], int rank[])
	{
		u = find(u, parent);
		v = find(v, parent);
		if(rank[u] < rank[v])
		{
			parent[u] = v;
		}
		else if(rank[v] < rank[u])
		{
			parent[v] = u;
		}
		else
		{
			parent[v] = u;
			rank[u]++;
		}
	}
	public int find(int v, int parent[])
	{
		if(parent[v] == v)
		{
			return v;
		}
		return find(parent[v], parent);
	}
	public void sort(way array[])
	{
		 int f = 0;;
		 int i;
	     way tmp;
	     while(f == 0)
	     {
	    	 f = 1;
	    	 for (i = 0; i < (k-1); i++)
	    	 {
	    		 if (array[i].value > array[i+1].value) 
	    		 {
	    			 f = 0;
	    			 tmp = array[i];
	    			 array[i] = array[i+1];
	    			 array[i+1] = tmp;
	    		 }
	    	 }
	     }
	}
	class point
	{
		public String name;
		public boolean visited;
		public int number;
		public point(String name, int number)
		{
			this.name = name;
			this.visited = false;
			this.number = number;
		}
	}
	class way 
	{
		 point begining, end;
		 int value;
		 public way() {}
		 public way(point begining, point end, int value)
		 {
			 values[begining.number][end.number] = value;
			 values[end.number][begining.number] = value;
			 this.begining = begining;
			 this.end = end;
			 this.value = value;
		 }
	}
	class stack
	{
		public int a[];
		public int top;
		public stack(int size)
		{
			a = new int[size];
			top = -1;
		}
		public void push(int i)
		{
			a[++top] = i;
		}
		public int pop()
		{
			return a[top--];
		}
		public int peek()
		{
			return a[top];
		}
		public boolean empty()
		{
			return top == -1;
		}
	}
    static class queue 
    {
    	public int size = 20;
        public int array[];
        public int front;
        public int back;
        public int c;
        public queue() 
        {
            array = new int[size];
            front = 0;
            back = -1;
            c = 0;
        }
        public void add(int j)
        {
            if (back == size - 1)
            {
                back = -1;
            }
            array[++back] = j;
            c++;
        }
        public int delete()
        {
            int tmp = array[front++];
            if (front == size)
            {
                front = 0;
            }
            c--;
            return tmp;
        }
        public boolean empty()
        {
        	if(c == 0)
        	{
        		return true;
        	}
            return false;
        }
    }
}