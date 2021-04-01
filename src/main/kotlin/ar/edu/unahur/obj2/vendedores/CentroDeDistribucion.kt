package ar.edu.unahur.obj2.vendedores



class CentroDeDistribucion(val ciudad: Ciudad) {

    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor : Vendedor){
        if(vendedores.contains(vendedor)) {
            error("Este vendedor ya se encuentra en la lista")
        }else{
            vendedores.add(vendedor)
        }
    }

    fun vendedorEstrella() {
         vendedores.maxBy { it.puntajeCertificaciones() }
    }


    fun puedeCubrir(ciudad : Ciudad){
        vendedores.any { it.puedeTrabajarEn(ciudad)}
    }



    fun vendedoresGenericos(){
        vendedores.filter{it.esGenerico()}
    }


    fun esRobusto(){
        this.vendedores.filter{it.esFirme()}.size >= 3
    }

}







