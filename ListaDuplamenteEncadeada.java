public class ListaDuplamenteEncadeada {
    No base;
    No topo;
    int tamanho = 0;


    public boolean isEmpty() {
        return tamanho==0; //se o tamanho for 0, ela está vazia
    }

    public boolean isFull() {
        return false; //lista encadeada nunca está cheia ;)
    }

    public int getSize() { //retorna o tamanho
        return tamanho;
    }

    public void add(int pos, int valor) {
        if (pos < 0 || pos > tamanho) { //se a posição não existir
            throw new IndexOutOfBoundsException("Posição inválida"); //exception
        }

        No novoNo = new No(valor); //cria o nó

        if (pos == 0) { // Adiciona no início
            if (isEmpty()) {
                base = novoNo;  //coloca a base e o topo associados ao nó já que só tem ele
                topo = novoNo;
            } else {
                novoNo.proximo = base;
                base.anterior = novoNo;
                base = novoNo;
            }
        } else if (pos == tamanho) { // Adiciona no final
            topo.proximo = novoNo; //o proximo do antigo topo é o novo nó
            novoNo.anterior = topo; // e o anterior do novo nó é o antigo topo
            topo = novoNo; //reatribui o topo para o novo valor | o novo nó criado
        } else { // Adiciona no meio
            No atual = base;
            for (int i = 0; i < pos - 1; i++) { //percorre até o nó anterior à posição desejada
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo; //atribui os dados do nó
            novoNo.anterior = atual;
            if (atual.proximo != null) { //se  nao for o topo
                atual.proximo.anterior = novoNo; //o novo nó é o anterior do próximo nó
            }
            atual.proximo = novoNo;
        }

        tamanho++; //aumenta o tamanho, pois adicionamos um nó
    }

    public int remove(int pos) {
        if (pos < 0 || pos >= tamanho) { //verifica se a posição é válida
            throw new IndexOutOfBoundsException("Posição inválida"); //exception
        }

        No removido;

        if (pos == 0) { // Remove do início
            removido = base;
            base = base.proximo; //atribui o valor do proximo nó como a nova base
            if (base != null) {
                base.anterior = null; //atribui o valor null para o nó anterior
            } else {
                topo = null; // lista fica vazia
            }
        } else if (pos == tamanho - 1) { // remove do final
            removido = topo; //o topo é o valor removido - removemos do final
            topo = topo.anterior; //o novo topo é o valor anterior a ele
            topo.proximo = null; //o proximo é nulo pois nao há mais valores depois dele
        } else { // Remove do meio
            No atual = base;
            for (int i = 0; i < pos; i++) { //percorre ate achar a posição desejada
                atual = atual.proximo; //o atual agora é o proximo valor depois dele
            }
            removido = atual;
            atual.anterior.proximo = atual.proximo; //redefine quem aponta pra quem, o proximo do anterior agora é o proximo do atual
            atual.proximo.anterior = atual.anterior; // o anterior do proximo agora é o anterior o atual
        }

        tamanho--; //diminui o tamanho
        return removido.dado; //retorna o valor removido
    }


    public int remove(No no) {
        if (no == null) { //verifica se é null - inválido
            throw new NullPointerException("Nó não pode ser nulo");
        }

        No atual = base; // Começa na base
        int pos = 0; // Contador de posição
        while (atual != null && atual != no) {
            // Avança na lista até encontrar o nó ou chegar ao final
            atual = atual.proximo;
            pos++;
        }

        if (atual == null) {
            // Verifica se o nó não foi encontrado
            throw new IllegalArgumentException("Nó não encontrado na lista");
        }

        return remove(pos); // Remove o nó usando o método de remoção por posição
    }


    public No getNo(int pos) {
        if (pos < 0 || pos >= tamanho) { //verifica a posição
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        No atual = base; //comeca na base
        for (int i = 0; i < pos; i++) { //itera até encontrar a posição desejada
            atual = atual.proximo;
        }
        return atual;
    }


    public int get(int pos) {
        return getNo(pos).dado;
    }

    public void set(int pos, int valor) {
        No no = getNo(pos);
        no.dado = valor;
    }
    public int find(int valor) {
        No atual = base;
        int pos = 0;
        while (atual != null) {
            if (atual.dado == valor) {
                return pos;
            }
            atual = atual.proximo;
            pos++;
        }
        return -1;
    }

}
