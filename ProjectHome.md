The Desktop Agent is a concept that is by far not original, but I haven't seen many good free implementations written in Java, so this is my attempt.  Basically, the idea is to create something that allows you to create sub-agents or bots that collect information for you.  You can make the agent persist on the desktop or hide somewhere, and you can customize it in many ways.  The Desktop Agent will act as a sort of framework used to create these sub-agents and will control them and launch them.

For now the desktop agent will use a non-plugin architecture, or a static architecture.

Two sub agent types that I want to create are
  1. Web Scraper - which makes it possible to go out onto the Internet and constantly watch a certain attribute, phrase, or whatever.
  1. Filesystem Notifier - which makes it possible to watch a certain directory or file to see if it changes or if something new appears there.