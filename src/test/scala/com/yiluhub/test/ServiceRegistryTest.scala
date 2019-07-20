package com.yiluhub.test

import com.yiluhub.registry.ServiceRegistry
import org.scalatest.FunSuite

class ServiceRegistryTest extends FunSuite{

  test("Test 1 - Register function") {
    val serviceName = "SimpleService"
    val registryUrl = "http://localhost:8000"

    println("Test 1")
    ServiceRegistry.register(serviceName, registryUrl)
    assert(ServiceRegistry.registryMap.size >= 1)
    //also could be implemented to check the name, or url
  }

  test("Test 2 - Unregister function") {
    val serviceName = "SimpleService"
    println("Test 2")
    ServiceRegistry.unregister(serviceName)
    assert(ServiceRegistry.registryMap.size == 0)
  }
}
