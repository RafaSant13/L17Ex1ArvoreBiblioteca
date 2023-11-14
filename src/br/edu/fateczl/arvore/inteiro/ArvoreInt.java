package br.edu.fateczl.arvore.inteiro;

public class ArvoreInt {

	No raiz;

	public ArvoreInt() {
		raiz = null;
	}
	
	public void insert(int dado) {
		No no = new No();
		no.dado = dado;
		no.esquerda = null;
		no.esquerda = null;
		insertleaf(no, raiz);
	}
	
	private void insertleaf(No no, No raizSubArvore) {
		if (raiz==null) {
			raiz = no;
		}
		else {
			if (no.dado < raizSubArvore.dado) {
				if (raizSubArvore.esquerda == null) {
					raizSubArvore.esquerda = no;
				}else {
					insertleaf(no, raizSubArvore.esquerda);
				}
			}
			if (no.dado > raizSubArvore.dado) {
				if (raizSubArvore.direita == null) {
					raizSubArvore.direita = no;
				}else {
					insertleaf(no, raizSubArvore.direita);
				}
			}
		}
		
	}
	
	public void search (int valor) throws Exception {
		try {
			No no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println(no.dado + ": Nível "+level);
		} catch (Exception e) {
			throw new Exception(valor+": Não existente");
		}
	}

	private int nodeLevel(No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return 1 + nodeLevel(raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return 1 + nodeLevel(raizSubArvore.direita, valor);
		}
		else {
			return 0;
		}
	}

	private No nodeSearch(No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return nodeSearch(raizSubArvore.direita, valor);
		}
		else {
			return raizSubArvore;
		}
	}
	
	public void prefixSearch() throws Exception {
		System.out.print("Pré-ordem: ");
		prefix(raiz);
		System.out.println("");
	}

	private void prefix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		System.out.print(raizSubArvore.dado+" ");
		if (raizSubArvore.esquerda!=null) {
			prefix(raizSubArvore.esquerda);
		}
		if (raizSubArvore.direita!=null) {
			prefix(raizSubArvore.direita);
		}
	}
	
	public void infixSearch() throws Exception {
		System.out.print("Em ordem: ");
		infix(raiz);
		System.out.println("");
	}

	private void infix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		if (raizSubArvore.esquerda!=null) {
			infix(raizSubArvore.esquerda);
		}
		System.out.print(raizSubArvore.dado+" ");
		if (raizSubArvore.direita!=null) {
			infix(raizSubArvore.direita);
		}
	}
	
	public void postfixSearch() throws Exception {
		System.out.print("Pós-ordem: ");
		postfix(raiz);
		System.out.println("");
	}

	private void postfix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		if (raizSubArvore.esquerda!=null) {
			postfix(raizSubArvore.esquerda);
		}
		if (raizSubArvore.direita!=null) {
			postfix(raizSubArvore.direita);
		}
		System.out.print(raizSubArvore.dado+" ");
	}
	
	public void remove(int valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor não existente");
		}
	}

	private void removeChild(No raizSubArvore, int valor) throws Exception{
		No no  = nodeSearch(raiz, valor);
		No pai = nodeParent(null, raiz, valor);
		if(no.direita != null && no.esquerda != null) {
			No noTroca = no.esquerda;
			while (noTroca.direita!= null) {
				noTroca = noTroca.direita;
			}
			pai = nodeParent(null, raiz, noTroca.dado);
			no.dado = noTroca.dado;
			noTroca.dado = valor;
			removeOneOrZeroLeaf(pai, noTroca);
		}
		else {
			removeOneOrZeroLeaf(pai, no);
		}
	}
	

	private No nodeParent(No pai, No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return nodeParent(raizSubArvore, raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return nodeParent(raizSubArvore, raizSubArvore.direita, valor);
		}
		else {
			if(pai == null) {
				return raizSubArvore;
			}
			else {
				return pai;
			}
		}
	}

	private void removeOneOrZeroLeaf(No pai, No no) {
		if (no.esquerda == null && no.direita == null) {
			change(pai, no, null);
		} else if (no.esquerda == null) {
			change(pai, no, no.direita);
		} else if (no.direita == null) {
			change(pai, no, no.esquerda);
		}
			
	}

	private void change(No pai, No no, No novoNo) {
		if (pai.esquerda != null && pai.esquerda.dado == no.dado) {
			pai.esquerda = novoNo;
		} else if (pai.direita.dado == no.dado) {
			pai.direita = novoNo;
		}
	}

}
