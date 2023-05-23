graph={}
nodes=list(map(str,input("Enter the nodes in the graph: ").split(" ")))
print(nodes)

edgwt=[[x,y] for x,y in zip(map(str,input("Enter the edges seperated by space :").split(" ")),map(int,input("Enter the weight for the edges: ").split(" ")))]
for i in nodes:
    graph[i]={}
    for j in edgwt:
        if(j[0][0]==i):
            graph[i][j[0][1]]=j[1]
        if(j[0][1]==i):
            graph[i][j[0][0]]=j[1]
print(graph)

h={}
for i in nodes:
    print("Enter heuristic values :"+i+":")
    h[i]=int(input())

def astar(startnode,stopnode):
    openset=set(startnode)
    closeset=set()

    g={}
    parent={}

    g[startnode]=0
    parent[startnode]=startnode
    while(len(openset)):
        n=None
        for v in openset:
            if n==None or g[v]+h[v]<g[n]+h[n]:
                n=v
        if(n==stopnode):
            pass

        else:
            for m,weight in graph[n].items():
                if(m not in openset and m not in closeset):
                    openset.add(m)
                    parent[m]=n
                    g[m]=g[n]+weight
                else:
                    if(g[m]>g[n]+weight):
                        g[m]=g[n]+weight
                        parent[m]=n

                        if(m in closeset):
                            closeset.remove(m)
                            openset.add(m)
        if (n==None):
            print("no path found")
            return None
        
        if(n==stopnode):
            path=[]
            while(parent[n]!=n):
                path.apend(n)
                n=parent[n]
            path.apend(startnode)
            path.reverse()
            print("path found:",path)
            return path
        openset.remove(m)
        closeset.add(m)
    print("Path does  not exist:")
    return None
astar('A','G')