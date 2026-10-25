package br.com.alura.bytebank.domain.cliente;

public record DadosCadastroCliente(String nome, String cpf, String email, boolean ativa) {
    public boolean ativa() {
        return ativa;
    }
}
