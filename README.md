# Test de Orientación Vocacional

## Descripción
Este proyecto consiste en una aplicación móvil nativa para Android diseñada para facilitar el proceso de descubrimiento profesional en estudiantes. La herramienta implementa un test psicométrico que evalúa aptitudes, intereses y habilidades del usuario para ofrecer un perfil de carreras sugeridas.

La arquitectura se basa en principios modernos de desarrollo móvil, asegurando una interfaz reactiva y una separación de responsabilidades clara entre la lógica de negocio y la presentación de datos.

## Tecnologías utilizadas
El desarrollo se ha llevado a cabo utilizando las siguientes herramientas y frameworks:

* **Lenguaje de programación:** Kotlin 1.9.x
* **Toolkit de interfaz de usuario:** Jetpack Compose (Material Design 3)
* **Entorno de desarrollo:** Android Studio (Versión Jellyfish o superior recomendada)
* **Gestión de dependencias:** Gradle (Kotlin DSL)
* **Arquitectura:** MVVM (Model-View-ViewModel)
* **Componentes de Jetpack:**
    * Compose Navigation para el flujo entre pantallas.
    * ViewModel para la gestión de estados.
    * StateFlow y LiveData para la reactividad de datos.

## Instrucciones para ejecutar la aplicación

### Requisitos previos
1. Tener instalado Android Studio en su versión más reciente.
2. Contar con el SDK de Android configurado (API nivel 24 como mínimo).
3. Java Development Kit (JDK) versión 17.

### Pasos para la instalación
1. **Clonar el repositorio:**
   Descargue el código fuente mediante Git utilizando el siguiente comando:
   ```bash
   git clone https://github.com/c04o/examen-poo2.git
   ```
2. Importar el proyecto:
Abra Android Studio y seleccione la opción "Open". Navegue hasta la carpeta donde clonó el repositorio y selecciónela.

3. Sincronización de Gradle:
Una vez abierto, el IDE detectará los archivos de configuración. Espere a que el proceso "Gradle Sync" finalice correctamente. Es necesario contar con conexión a internet para descargar las dependencias listadas en el archivo build.gradle.kts.

4. Configuración del dispositivo:
Puede utilizar un dispositivo físico mediante la depuración USB o un dispositivo virtual (Emulador) configurado en el Device Manager de Android Studio. Se recomienda un dispositivo con API 30 o superior para un rendimiento óptimo.

5. Compilación y ejecución:
Presione el botón "Run" (icono de flecha verde) en la barra de herramientas superior o utilice el atajo Shift + F10. La aplicación se instalará y abrirá automáticamente en el dispositivo seleccionado.