// Copyright Epic Games, Inc. All Rights Reserved.

#include "FPSTestGameMode.h"
#include "FPSTestCharacter.h"
#include "UObject/ConstructorHelpers.h"

AFPSTestGameMode::AFPSTestGameMode()
	: Super()
{
	// set default pawn class to our Blueprinted character
	static ConstructorHelpers::FClassFinder<APawn> PlayerPawnClassFinder(TEXT("/Game/FirstPerson/Blueprints/BP_FirstPersonCharacter"));
	DefaultPawnClass = PlayerPawnClassFinder.Class;

}
