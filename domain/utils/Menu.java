package domain.utils;

import utils.EstiloTerminal;

public class Menu {
    // Menu: Start  -----------------------------------------------------------------------------------------------------------------------------------------
    public static void menuInicial() {
        EstiloTerminal.spinner("Carregando:  Inicial ", 1000);
        System.out.println(EstiloTerminal.estilizar("╔═══════════════════════════════════════════════════════════════╗", true, false, EstiloTerminal.VERDE));
        System.out.println(EstiloTerminal.estilizar("║", true, false, EstiloTerminal.VERDE)
                + EstiloTerminal.estilizar("                     Sistema AdotaPetCLI                       ", true, false, EstiloTerminal.VERDE)
                + EstiloTerminal.estilizar("║", true, false, EstiloTerminal.VERDE));
        System.out.println(EstiloTerminal.estilizar("╚═══════════════════════════════════════════════════════════════╝", true, false, EstiloTerminal.VERDE));
        System.out.println(EstiloTerminal.estilizar("      >>> Bem-vindo ao sistema de cadastros AdotaPetCLI <<<     ", true, true, EstiloTerminal.CINZA));

    }

    // Menu: Cadastro  -----------------------------------------------------------------------------------------------------------------------------------------
    public static void menuCadastro() {
        EstiloTerminal.spinner("Carregando: Menu Cadastro", 600);
        System.out.println(EstiloTerminal.estilizar("\nDigite a opção desejada:", true, true, EstiloTerminal.VERDE));

        System.out.println(EstiloTerminal.estilizar(" [1] Cadastrar um novo pet", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" [2] Alterar os dados do pet cadastrado", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" [3] Deletar um pet cadastrado", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" [4] Listar todos os pets cadastrados", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" [5] Listar pets por algum critério (idade, nome, raça)", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" [6] Sair", true, false, EstiloTerminal.AMARELO));
        System.out.println(EstiloTerminal.estilizar(" >>> ", true, true, EstiloTerminal.VERDE));
    }

    // Menu: Buscar -----------------------------------------------------------------------------------------------------------------------------------------
    public static void menuBuscar() {
        EstiloTerminal.spinner("Carregando: Menu Buscar", 600);
        System.out.println(utils.EstiloTerminal.estilizar("[1] Nome", true, false, EstiloTerminal.AMARELO));
        System.out.println(utils.EstiloTerminal.estilizar("[2] Sexo", true, false, EstiloTerminal.AMARELO));
        System.out.println(utils.EstiloTerminal.estilizar("[3] Idade", true, false, EstiloTerminal.AMARELO));
        System.out.println(utils.EstiloTerminal.estilizar("[4] Peso", true, false, EstiloTerminal.AMARELO));
        System.out.println(utils.EstiloTerminal.estilizar("[5] Raça", true, false, EstiloTerminal.AMARELO));
        System.out.println(utils.EstiloTerminal.estilizar("[6] Endereço", true, false, EstiloTerminal.AMARELO));
    }
}
