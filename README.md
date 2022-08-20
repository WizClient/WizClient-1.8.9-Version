# IMPORTANT INFORMATION


## I am currently working on changing the client into a mixin client, with the mixin of [spongepowered](https://github.com/SpongePowered). There will be no commit until I switch to a mixin client. This will take some time, as I need to learn the mixin and convert the project. Thanks for your understanding :heart:


---

### WizClient-1.8.9-Version
Minecraft 1.8.9 PVP client source code (very wip and bugy)

:warning: **This client is at the beginning of its development, it is ugly, there is no interface, it is not very optimized, there are many bugs. Be indulgent**


# How to install (boring):

1. **Get mcp of the 1.8.9 minectaft version** | [Youtube video for setup mcp 1.8.9](https://www.youtube.com/watch?v=b2gJfKNSb1k)
2. **Remove**: 

- ``net/minecraft/client/gui/GuiStreamIndicator.java``
- ``net/minecraftclient/gui/stream``
- ``net/minecraftclient/stream``

3. **Get all files from the github projet and copy past into your mcp project** ``except for the eclipse file``.**Replace all files that will be the same.**
4. **Fix all the errors that your IDE will tell you and don't forget to add the librairy from the lib folder**

:warning: If when you run it, it still doesn't work. I'm sorry, you'll have to google it because it's not the project.

:fire: Congratulations! You have installed the wizclient
