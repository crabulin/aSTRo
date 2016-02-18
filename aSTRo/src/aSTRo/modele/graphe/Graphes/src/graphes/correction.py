# -*- coding: utf-8 -*-

class GrapheNO:
    """graphe non orienté codé par liste d'adjacence. Les sommets devront etre numerotes 0,1,...,n-1"""

    def __init__(self, n, l_adj):
        """initialise un graphe d'apres la liste d'adjacence l_adj et son ordre n""" 

        self.ordre = n          # attribut ordre = nb de sommets
        self.adj = l_adj        # attribut liste adjacence


    def affiche(self):
        """affiche dans le terminal la liste d'adjacence"""
        print "ordre : ", self.ordre
        for v in range(self.ordre):
            print "voisins de", v," : ",
            for voisin in self.adj[v]:
                print voisin,
            print

    def degre(self,v):
        """renvoie le degre du sommet v"""
        return len(self.adj[v])

    def taille(self):
        """renvoie le nombre d'arêtes du graphe"""
        sommedegres = 0
        for v in range(self.ordre):
            sommedegres += len(self.adj[v])
        return sommedegres/2
        # petits malins : return sum([len(self.adj[v]) for v in range(self.ordre)])

    def nbTriangles(self):
        """renvoie le nb de triangles du graphe. Une version très naïve consisterait à faire une triple
        boucle et tester les trois adjacences. 
        Mieux vaut chercher les voisins des voisins d'un sommet v donné et regarder si v est voisin à nouveau.
        Chaque triangle aura été compté six fois."""

        nbT = 0
        for v in range(self.ordre):
            for v1 in self.adj[v]:
                for v2 in self.adj[v1]:
                    if v in self.adj[v2]:
                        nbT += 1
        return nbT/6

####################################################################################################################


def aretes_vers_liste_adjacence(n,aretes):
    """n est l'ordre du graphe non oriente, sommets 0,1, ..., n-1
    aretes la liste de ses aretes 
    renvoie la liste d'adjacence du graphe"""

    adjacence = [ [] for i in range(n) ]   #liste contenant n listes vides
    for arete in aretes:
        adjacence[ arete[0] ].append( arete[1] )
        adjacence[ arete[1] ].append( arete[0] )

    return adjacence


def grapheComplet(n):
    """renvoie un objet GrapheNO correspondant au graphe complet d'ordre n"""
    l_adj = []
    for v in range(n):
        voisinage = []
        for voisin in range(n):
            if voisin != v:
                voisinage.append(voisin)
        l_adj.append(voisinage)

    return GrapheNO(n, l_adj)

    """remarque : on peut également si on est en forme écrire une seule ligne :
    return GrapheNO([ [j for j in range(n) if j!=i] for i in range(n) ])"""


def cycle(n) :
    """renvoie un objet GrapheNO correspondant au cycle d'ordre n"""
    l_adj = [[1,n-1]]
    for v in range(1,n-1):
        l_adj.append([v-1,v+1])
    l_adj.append([n-2,0])
    return GrapheNO(n, l_adj)


def lireAretesEtOrdre(nomdufichier):
    """lit le fichier et renvoie la liste des aretes qui s'y trouvent"""
    f = file(nomdufichier, 'r')
    lignes = f.readlines()
    #on extrait les lignes qui commencent par 'E' 
    #si c'est bon on cree une nouvelle arete
    aretes = []
    ordre = 0
    for l in lignes:
        mots = l.split()
        if len(mots) >= 3 and mots[0]=='E':
            aretes.append([int(mots[1]), int(mots[2])])  #tout la monde va oublier la conversion en int
        if len(mots) > 0 and mots[0]=="ordre":
            ordre = int(mots[1])
    return aretes, ordre


def lireGrapheNO(nomdufichier):
    """renvoie l'objet GrapheNO contenu par liste d'arêtes dans le fichier
    nomdufichier est une chaine de caracteres"""
    aretes, n = lireAretesEtOrdre(nomdufichier)
    return GrapheNO(n, aretes_vers_liste_adjacence(n,aretes))


def parcours_largeur(g, v):
    """effectue un parcours en largeur du grapheNO g depuis le sommet v ; 
    renvoie le tableau d des distances et le tableau pred des predecesseurs ;
    utilise une file LIFO basique avec une liste (queue)
    ici version sans les couleurs"""

    #initialisation
    pred = [None] * g.ordre
    d = [-1] * g.ordre
    d[v] = 0
    queue = [v]

    #on y va
    while queue:
        courant = queue.pop()
        for voisin in g.adj[courant]:
            if d[voisin] == -1:
                pred[voisin] = courant
                d[voisin] = d[courant] + 1
                queue.insert(0,voisin)

    #fin
    return d, pred

def nb_composantes_connexes(g):
    """compte le nombre de composantes connexes du graphe g"""
    inconnu = [True] * g.ordre
    nb_composantes = 0
    for depart in range(g.ordre):
        # si le sommet depart est inconnu, on lance un parcours"""
        if inconnu[depart]:
            nb_composantes +=1
            queue = [depart]
            inconnu[depart] = False
            while queue:
                courant = queue.pop()
                for voisin in g.adj[courant]:
                    if inconnu[voisin]:
                        inconnu[voisin] = False
                        queue.insert(0,voisin)
                        inconnu[voisin] = False
    return nb_composantes

















