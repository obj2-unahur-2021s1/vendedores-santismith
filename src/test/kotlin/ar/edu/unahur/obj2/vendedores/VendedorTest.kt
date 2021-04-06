package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import ar.edu.unahur.obj2.vendedores.CentroDeDistribucion as CentroDeDistribucion

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)
  val certificacion1 = Certificacion(true,10)
  val certificacion2=  Certificacion(false,20)
  val certificacion3 = Certificacion(true,30)
  val neuquen = Provincia(619745)
  val cutralco = Ciudad(neuquen)
  val centenario = Ciudad(neuquen)
  val sanMartin = Ciudad(neuquen)
  val plottier = Ciudad(neuquen)
  val zapala = Ciudad(neuquen)
  val ciudades : List<Ciudad> = listOf(cutralco,centenario,sanMartin,plottier,zapala)
  val ciudades2 : List<Ciudad> = listOf(cutralco,centenario,sanMartin)


  val corresponsal = ComercioCorresponsal(ciudades)
  val corresponsal2 = ComercioCorresponsal(ciudades2)



  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
  }

  describe("Vendedor fijo, y vendedor viajante : Funcion es influyente"){
    val jujuy = Provincia(30000)
    val tilcara = Ciudad(jujuy)
    val vendedorFijo = VendedorFijo(tilcara)
    val viajante = Viajante(listOf(jujuy))

    describe("Vendedor fijo no es influyente"){
      it("Un vendedor nunca es influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }

    describe("Viajante no es influyente"){
      it("vendedor viajante no es influyente ya que esta habilitado solo en jujuy"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }


  describe("Comercio corresponsal es influyente"){


    describe("Comercio corresponsal 1"){
      it("Comercio corresponsal con 5 ciudades es influyente"){
        corresponsal.esInfluyente().shouldBeTrue()
      }
    }

    describe("Comercio corresponsal 2"){
      it("comercio corresponsal con 3 ciudades no es influyente"){
        corresponsal2.esInfluyente().shouldBeFalse()
      }
    }
  }


  describe("vendedor fijo test funcion esVersatil() y test funcion esFirme"){
      val vendedorFijo = VendedorFijo(sanIgnacio)
      vendedorFijo.agregarCertificacion(certificacion1)
    it("Vendedor fijo no es versatil ya que tiene solo una certificacion"){
      vendedorFijo.esVersatil().shouldBeFalse()
    }

    it("vendedor con 1 certificacion de 10 puntos no es firme"){
      vendedorFijo.esFirme().shouldBeFalse()
    }

    it("agrego las 2 certificaciones y ahora si tiene que ser versatil"){
      vendedorFijo.agregarCertificacion(certificacion2)
      vendedorFijo.agregarCertificacion(certificacion3)
      vendedorFijo.esVersatil().shouldBeTrue()
    }

    it("vendedor con 3 certificaciones de 60 puntos es firme"){
      vendedorFijo.esFirme().shouldBeTrue()
    }
  }






  describe("Centro De Distribucion"){
    val catamarca = Provincia(396895)
    val tinogasta = Ciudad(catamarca)
    val centroDeDistribucion = CentroDeDistribucion(tinogasta)
    val vendedorFijo = VendedorFijo(sanIgnacio)
    val viajante = Viajante(listOf(misiones))

    centroDeDistribucion.agregarVendedor(vendedorFijo)
    centroDeDistribucion.agregarVendedor(viajante)
    centroDeDistribucion.agregarVendedor(corresponsal)

    vendedorFijo.agregarCertificacion(certificacion2)
    viajante.agregarCertificacion(certificacion3)
    corresponsal.agregarCertificacion(certificacion1)

    it("test agregar funcion(error)"){
      shouldThrowAny{
        centroDeDistribucion.agregarVendedor(vendedorFijo)
      }
    }

//    it("test vendedor estrella"){
//      centroDeDistribucion.vendedorEstrella().shouldBe
//    }

//    it("test puede cubrir"){
//      centroDeDistribucion.puedeCubrir(sanIgnacio)
//    }

//    it("vendedores genericos"){
//      centroDeDistribucion.vendedoresGenericos().should
//    }


//    it("esRobusto"){}


  }



})
