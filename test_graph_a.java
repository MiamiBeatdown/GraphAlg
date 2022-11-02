public class test_graph_a 
{

    public static void main(String[] args)
    {
        graph_a a = new graph_a(7);
        a.addPoint("A");
        a.addPoint("B");
        a.addPoint("C");
        a.addPoint("D");
        a.addPoint("E");
        a.addPoint("F");
        a.addPoint("G");

        a.addWay("A", "B",1);		//      A-(1)-B
        a.addWay("A", "C", 2);		//      |\    |\
        a.addWay("A", "D", 4);		//      | \   |(5)
        a.addWay("B", "E", 5);		//     (2)(4)(2) E
        a.addWay("B", "D", 2);		//      |   \ |(1)
        a.addWay("C", "F", 4);		//      |    \|/
        a.addWay("C", "D", 7);		//      C-(7)-D
        a.addWay("C", "G", 1);		//      |\    /
        a.addWay("D", "G", 1);		//      |(1)(1)
        a.addWay("D", "E", 1);		//     (4) \/
        							//      |   G
        System.out.println("dfs:"); //      F 
        a.dfs("A");
        System.out.println("bfs:");  
        a.bfs("A");
        System.out.println("Dijkstra:");  
        a.Dijkstra("A");
        System.out.println("Floyd Warshall:");
        a.Floyd_Warshall();
        System.out.println("Prim:");
        a.Prim();
        System.out.println("Kruskal:");
        a.Kruskal();
    }
}
