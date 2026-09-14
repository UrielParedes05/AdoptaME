# Implementación de Pantalla de Login - Patitas App

Este plan detalla la creación de la interfaz de usuario para la pantalla de inicio de sesión basada en la imagen proporcionada. La implementación se realizará utilizando Jetpack Compose y Material 3.

## User Review Required

> [!IMPORTANT]
> - Se utilizarán iconos estándar de Material Design para el logo de la patita, el sobre (email) y el candado (password), ya que no hay recursos gráficos específicos en el proyecto todavía.
> - Los botones de "Google" y "Apple" serán representados con iconos y texto básicos.

## Proposed Changes

### UI & Theme

#### [MODIFY] [Color.kt](file:///C:/Users/USUARIO/AndroidStudioProjects/AdoptaME/app/src/main/java/com/example/adoptame/ui/theme/Color.kt)
- Definir la paleta de colores de la aplicación:
    - `PrimaryCoral`: `#FF7051`
    - `BackgroundColor`: `#FFF9F6`
    - `SecondaryText`: `#8A8A8A`

#### [MODIFY] [Theme.kt](file:///C:/Users/USUARIO/AndroidStudioProjects/AdoptaME/app/src/main/java/com/example/adoptame/ui/theme/Theme.kt)
- Configurar el `LightColorScheme` para usar los nuevos colores.

#### [NEW] [LoginScreen.kt](file:///C:/Users/USUARIO/AndroidStudioProjects/AdoptaME/app/src/main/java/com/example/adoptame/ui/LoginScreen.kt)
- Crear el componente `LoginScreen` que contendrá:
    - Header con Logo y Título ("Patitas App").
    - Campos de entrada para Correo electrónico y Contraseña.
    - Botones de acción principal (Iniciar Sesión, Crear Cuenta).
    - Sección de login social (Google, Apple).
    - Footer con mensaje "Adopta, no compres ❤️".

### MainActivity

#### [MODIFY] [MainActivity.kt](file:///C:/Users/USUARIO/AndroidStudioProjects/AdoptaME/app/src/main/java/com/example/adoptame/MainActivity.kt)
- Reemplazar el `Greeting` por `LoginScreen` dentro del `Scaffold`.

## Verification Plan

### Automated Tests
- No se incluyen en esta fase inicial de UI, pero se pueden añadir pruebas de captura de pantalla posteriormente.

### Manual Verification
- Renderizar la vista previa de Compose (`LoginPreview`) en Android Studio para verificar la fidelidad visual con la imagen original.
- Desplegar en un emulador/dispositivo para verificar el comportamiento de los campos de texto e interactividad básica.
