package com.ingressou.Ingressou;

import com.ingressou.Ingressou.model.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoTest {

    private Evento evento;

    @BeforeEach
    public void setUp() {
        evento = new Evento();
        evento.setNome("Evento de Teste");
        evento.setDescricao("Descrição do Evento de Teste");
        evento.setDataEvento(LocalDate.now());
        evento.setLocalEvento("Tubarão");
        evento.setFaixaEtaria(18);
        evento.setImagemUrl("url_imagem");
        evento.setHoraAbertura(LocalTime.of(18, 0));
        evento.setHoraInicio(LocalTime.of(20, 0));
        evento.setCapacidade(100);
        evento.setQuantidadeIngressos(30); // Por exemplo, 30 ingressos vendidos
    }

    @Test
    public void testCalcularIngressosDisponiveis() {
        int disponibilidadeEsperada = 70; // 100 - 30
        assertEquals(disponibilidadeEsperada, evento.calcularIngressosDisponiveis());
    }

    @Test
    public void testCalcularIngressosDisponiveisComIngressosVendidosZero() {
        evento.setQuantidadeIngressos(0);
        int disponibilidadeEsperada = 100; // Todos os ingressos disponíveis
        assertEquals(disponibilidadeEsperada, evento.calcularIngressosDisponiveis());
    }

    @Test
    public void testCalcularIngressosDisponiveisComTodosIngressosVendidos() {
        evento.setQuantidadeIngressos(100);
        int disponibilidadeEsperada = 0; // Nenhum ingresso disponível
        assertEquals(disponibilidadeEsperada, evento.calcularIngressosDisponiveis());
    }

    @Test
    public void testListar() {
        // Chama o método listar
        String resultado = evento.listar();

        // Monta o resultado esperado com base nos parâmetros do evento
        String resultadoEsperado = "Nome: " + evento.getNome() +
                ", Data: " + evento.getDataEvento() +
                ", Local: " + evento.getLocalEvento() +
                ", Disponibilidade: " + evento.calcularIngressosDisponiveis() + " ingressos disponíveis.";

        // Print do resultado e do esperado
        System.out.println("Resultado obtido: " + resultado);
        System.out.println("Resultado esperado: " + resultadoEsperado);

        // Verifica se o resultado está correto
        assertEquals(resultadoEsperado, resultado);

        // Verificando individualmente cada parâmetro e printando
        assertEquals("Evento de Teste", evento.getNome());
        System.out.println("Nome verificado: " + evento.getNome());

        assertEquals("Descrição do Evento de Teste", evento.getDescricao());
        System.out.println("Descrição verificada: " + evento.getDescricao());

        assertEquals(LocalDate.now(), evento.getDataEvento());
        System.out.println("Data verificada: " + evento.getDataEvento());

        assertEquals("Tubarão", evento.getLocalEvento());
        System.out.println("Local verificado: " + evento.getLocalEvento());

        assertEquals(18, evento.getFaixaEtaria());
        System.out.println("Faixa etária verificada: " + evento.getFaixaEtaria());

        assertEquals("url_imagem", evento.getImagemUrl());
        System.out.println("URL da imagem verificada: " + evento.getImagemUrl());

        assertEquals(LocalTime.of(18, 0), evento.getHoraAbertura());
        System.out.println("Hora de abertura verificada: " + evento.getHoraAbertura());

        assertEquals(LocalTime.of(20, 0), evento.getHoraInicio());
        System.out.println("Hora de início verificada: " + evento.getHoraInicio());

        assertEquals(100, evento.getCapacidade());
        System.out.println("Capacidade verificada: " + evento.getCapacidade());

        assertEquals(30, evento.getQuantidadeIngressos());
        System.out.println("Quantidade de ingressos verificada: " + evento.getQuantidadeIngressos());
    }


    @Test
    public void testSetNome() {
        evento.setNome("Novo Evento");
        assertEquals("Novo Evento", evento.getNome());
    }
}
