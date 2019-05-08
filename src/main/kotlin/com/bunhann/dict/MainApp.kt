package com.bunhann.dict

import com.bunhann.dict.data.Repository
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp : Application() {

    private lateinit var stage: Stage

    companion object{
        lateinit var instance: MainApp
    }

    init {
        instance = this
    }

    override fun start(primaryStage: Stage) {
       stage = primaryStage

        val repository = Repository()
        repository.onStart()
        if (repository.isDataLoaded()) {
            changeScene("main.fxml")
        } else {
            changeScene("loader.fxml")
        }

        primaryStage.show()
    }

    private fun changeScene(fxml: String, title: String = "Dictionary") {
        val page = FXMLLoader.load(javaClass.classLoader.getResource(fxml)) as Parent
        var scene = stage.scene
        stage.title = title
        if ( scene == null) {
            scene = Scene(page, 900.0, 700.0)
            stage.scene = scene
        } else {
            stage.scene.root = page
        }

        stage.sizeToScene()

    }

    fun onDictionaryLoaded() {
        changeScene("main.fxml")
    }
}