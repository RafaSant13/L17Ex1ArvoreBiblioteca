package br.edu.fateczl.arvore.caractere;

public class ArvoreChar {

	No raiz;

	public ArvoreChar() {
		raiz = null;
	}
	
	public void insert(char caractere) {
		caractere = Character.toUpperCase(caractere);
		No no = new No();
		no.dado = caractere;
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
	
	public void search (char caractere) throws Exception {
		caractere = Character.toUpperCase(caractere);
		try {
			No no = nodeSearch(raiz, caractere);
			int level = nodeLevel(raiz, caractere);
			System.out.println(no.dado + ": Nível "+level);
		} catch (Exception e) {
			throw new Exception(caractere+": Não existente");
		}
	}

	private int nodeLevel(No raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return 1 + nodeLevel(raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return 1 + nodeLevel(raizSubArvore.direita, caractere);
		}
		else {
			return 0;
		}
	}

	private No nodeSearch(No raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return nodeSearch(raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return nodeSearch(raizSubArvore.direita, caractere);
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
	
	public void remove(char caractere) throws Exception {
		caractere = Character.toUpperCase(caractere);
		try {
			removeChild(raiz, caractere);
		} catch (Exception e) {
			throw new Exception("Valor não existente");
		}
	}

	private void removeChild(No raizSubArvore, char caractere) throws Exception{
		No no  = nodeSearch(raiz, caractere);
		No pai = nodeParent(null, raiz, caractere);
		if(no.direita != null && no.esquerda != null) {
			No noTroca = no.esquerda;
			while (noTroca.direita!= null) {
				noTroca = noTroca.direita;
			}
			pai = nodeParent(null, raiz, noTroca.dado);
			no.dado = noTroca.dado;
			noTroca.dado = caractere;
			removeOneOrZeroLeaf(pai, noTroca);
		}
		else {
			removeOneOrZeroLeaf(pai, no);
		}
	}
	

	private No nodeParent(No pai, No raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return nodeParent(raizSubArvore, raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return nodeParent(raizSubArvore, raizSubArvore.direita, caractere);
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
