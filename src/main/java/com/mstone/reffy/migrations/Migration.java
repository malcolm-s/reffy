package com.mstone.reffy.migrations;

public interface Migration {
  String getName();
  void run();
}
