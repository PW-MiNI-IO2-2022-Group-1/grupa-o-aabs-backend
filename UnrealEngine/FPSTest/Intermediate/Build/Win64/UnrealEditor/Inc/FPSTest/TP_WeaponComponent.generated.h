// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/ObjectMacros.h"
#include "UObject/ScriptMacros.h"

PRAGMA_DISABLE_DEPRECATION_WARNINGS
class AFPSTestCharacter;
#ifdef FPSTEST_TP_WeaponComponent_generated_h
#error "TP_WeaponComponent.generated.h already included, missing '#pragma once' in TP_WeaponComponent.h"
#endif
#define FPSTEST_TP_WeaponComponent_generated_h

#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_SPARSE_DATA
#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_RPC_WRAPPERS \
 \
	DECLARE_FUNCTION(execEndPlay); \
	DECLARE_FUNCTION(execFire); \
	DECLARE_FUNCTION(execAttachWeapon);


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_RPC_WRAPPERS_NO_PURE_DECLS \
 \
	DECLARE_FUNCTION(execEndPlay); \
	DECLARE_FUNCTION(execFire); \
	DECLARE_FUNCTION(execAttachWeapon);


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_INCLASS_NO_PURE_DECLS \
private: \
	static void StaticRegisterNativesUTP_WeaponComponent(); \
	friend struct Z_Construct_UClass_UTP_WeaponComponent_Statics; \
public: \
	DECLARE_CLASS(UTP_WeaponComponent, UActorComponent, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(UTP_WeaponComponent)


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_INCLASS \
private: \
	static void StaticRegisterNativesUTP_WeaponComponent(); \
	friend struct Z_Construct_UClass_UTP_WeaponComponent_Statics; \
public: \
	DECLARE_CLASS(UTP_WeaponComponent, UActorComponent, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(UTP_WeaponComponent)


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_STANDARD_CONSTRUCTORS \
	/** Standard constructor, called after all reflected properties have been initialized */ \
	NO_API UTP_WeaponComponent(const FObjectInitializer& ObjectInitializer); \
	DEFINE_DEFAULT_OBJECT_INITIALIZER_CONSTRUCTOR_CALL(UTP_WeaponComponent) \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, UTP_WeaponComponent); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(UTP_WeaponComponent); \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API UTP_WeaponComponent(UTP_WeaponComponent&&); \
	NO_API UTP_WeaponComponent(const UTP_WeaponComponent&); \
public:


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_ENHANCED_CONSTRUCTORS \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API UTP_WeaponComponent(UTP_WeaponComponent&&); \
	NO_API UTP_WeaponComponent(const UTP_WeaponComponent&); \
public: \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, UTP_WeaponComponent); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(UTP_WeaponComponent); \
	DEFINE_DEFAULT_CONSTRUCTOR_CALL(UTP_WeaponComponent)


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_11_PROLOG
#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_GENERATED_BODY_LEGACY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_RPC_WRAPPERS \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_INCLASS \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_STANDARD_CONSTRUCTORS \
public: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


#define FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_GENERATED_BODY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_RPC_WRAPPERS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_INCLASS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_14_ENHANCED_CONSTRUCTORS \
private: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


template<> FPSTEST_API UClass* StaticClass<class UTP_WeaponComponent>();

#undef CURRENT_FILE_ID
#define CURRENT_FILE_ID FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h


PRAGMA_ENABLE_DEPRECATION_WARNINGS
