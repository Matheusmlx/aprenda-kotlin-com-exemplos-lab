import java.text.Normalizer.Form

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val id: Int, val nome: String)
// Adicionado data class para a class Usuario, porque se trata de uma class que precisara guardar informações, informações essas como nome e um identificador
data class ConteudoEducacional(val id: Int, val nome: String, val duracao: Int = 10)
// alteração do tipo de variavel var para val, pois a variavel nome não sofre nenhuma modificação
// Alterado o valor default do parametro duracao para 10, pois achei 60 muito alto e null não pode ocorrer
data class Formacao(val nome: String,val nivel: Nivel,  val conteudos: List<ConteudoEducacional>) {

    private val inscritos = mutableListOf<Usuario>()
    //Abstrai a propriedade inscritos pois se trata de uma informação da class.
    
    fun matricular(vararg usuario: Usuario) {
        inscritos.addAll(usuario)
    }
    // Nos primeiros cenarios de testes estava passando um Usuario por vez, mas lembrei que existia o parametro vararg e imaginando que esse programa cresce, enviar um Usuario por vez não seria muito
    // produtivo. Por isso que optei em alterar para vararg

    fun buscarInscritos(): List<Usuario> = inscritos

    fun buscarConteudos(): List<ConteudoEducacional> = conteudos
}

fun main() {
    val listaConteudoEducacionalJavascript = mutableListOf<ConteudoEducacional>(
        ConteudoEducacional(1,"Formação POO", 30),
        ConteudoEducacional(2, "Conceitos Basicos da Linguagem", 30),
        ConteudoEducacional(3, "Funções", 30),
        ConteudoEducacional(4, "Listas", 30),
        ConteudoEducacional(5, "Boas Práticas", 30),
    )

    //Adicionando uma turma de alunos
    val primeiraTurma: List<Usuario> = listOf(
        Usuario(1, "Matheus"),
        Usuario(2, "Luiz"),
        Usuario(3, "Leticia"),
        Usuario(4, "Celso")
    )

    Formacao("Javascript", Nivel.INTERMEDIARIO, listaConteudoEducacionalJavascript).let {
        it.matricular(*primeiraTurma.toTypedArray())
        println("Formação: ${it.nome} , Nivel: ${it.nivel}, Conteudos Educacionais ${it.buscarConteudos()}")
        println(it.buscarInscritos())
    }

    //Testando se os valores defaults estão funcionando
    val listaConteudoEducacionalNode = mutableListOf<ConteudoEducacional>(
        ConteudoEducacional(1, "Conceitos Basicos"),
        ConteudoEducacional(2, "Conceitos Intermediarios", 60),
        ConteudoEducacional(3, "Express"),
        ConteudoEducacional(4, "Criando sua primeira API", 30),
    )

    //Adicionando um aluno por vez
    Formacao("Formação NodeJs", Nivel.BASICO, listaConteudoEducacionalNode).let {
        it.matricular(Usuario(1, "Paulo"))
        it.matricular(Usuario(4, "Julio"))
        println("Formação: ${it.nome} , Nivel: ${it.nivel}, Conteudos Educacionais ${it.buscarConteudos()}")
        println(it.buscarInscritos())
    }

    //Testando se os valores defaults estão funcionando
    // Criando uma Formação no Nivel Dificil
    val listaConteudoEducacionalSql = mutableListOf<ConteudoEducacional>(
        ConteudoEducacional(1, "Conceitos Basicos"),
        ConteudoEducacional(2, "Conceitos Intermediarios", 60),
        ConteudoEducacional(3, "Conceitos Avançados"),
        ConteudoEducacional(4, "Trabalhando com milhoes de registros", 30),
    )

    Formacao("Formação DBA", Nivel.DIFICIL, listaConteudoEducacionalSql).let {
        it.matricular(Usuario(1, "Paulo"))
        it.matricular(Usuario(4, "Julio"))
        println("Formação: ${it.nome} , Nivel: ${it.nivel}, Conteudos Educacionais ${it.buscarConteudos()}")
        println(it.buscarInscritos())
    }
}
