// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
#include "FPSTest/TP_WeaponComponent.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeTP_WeaponComponent() {}
// Cross Module References
	FPSTEST_API UClass* Z_Construct_UClass_UTP_WeaponComponent_NoRegister();
	FPSTEST_API UClass* Z_Construct_UClass_UTP_WeaponComponent();
	ENGINE_API UClass* Z_Construct_UClass_UActorComponent();
	UPackage* Z_Construct_UPackage__Script_FPSTest();
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestCharacter_NoRegister();
	ENGINE_API UEnum* Z_Construct_UEnum_Engine_EEndPlayReason();
	COREUOBJECT_API UClass* Z_Construct_UClass_UClass();
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestProjectile_NoRegister();
	ENGINE_API UClass* Z_Construct_UClass_USoundBase_NoRegister();
	ENGINE_API UClass* Z_Construct_UClass_UAnimMontage_NoRegister();
	COREUOBJECT_API UScriptStruct* Z_Construct_UScriptStruct_FVector();
// End Cross Module References
	DEFINE_FUNCTION(UTP_WeaponComponent::execEndPlay)
	{
		P_GET_PROPERTY(FByteProperty,Z_Param_EndPlayReason);
		P_FINISH;
		P_NATIVE_BEGIN;
		P_THIS->EndPlay(EEndPlayReason::Type(Z_Param_EndPlayReason));
		P_NATIVE_END;
	}
	DEFINE_FUNCTION(UTP_WeaponComponent::execFire)
	{
		P_FINISH;
		P_NATIVE_BEGIN;
		P_THIS->Fire();
		P_NATIVE_END;
	}
	DEFINE_FUNCTION(UTP_WeaponComponent::execAttachWeapon)
	{
		P_GET_OBJECT(AFPSTestCharacter,Z_Param_TargetCharacter);
		P_FINISH;
		P_NATIVE_BEGIN;
		P_THIS->AttachWeapon(Z_Param_TargetCharacter);
		P_NATIVE_END;
	}
	void UTP_WeaponComponent::StaticRegisterNativesUTP_WeaponComponent()
	{
		UClass* Class = UTP_WeaponComponent::StaticClass();
		static const FNameNativePtrPair Funcs[] = {
			{ "AttachWeapon", &UTP_WeaponComponent::execAttachWeapon },
			{ "EndPlay", &UTP_WeaponComponent::execEndPlay },
			{ "Fire", &UTP_WeaponComponent::execFire },
		};
		FNativeFunctionRegistrar::RegisterFunctions(Class, Funcs, UE_ARRAY_COUNT(Funcs));
	}
	struct Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics
	{
		struct TP_WeaponComponent_eventAttachWeapon_Parms
		{
			AFPSTestCharacter* TargetCharacter;
		};
		static const UECodeGen_Private::FObjectPropertyParams NewProp_TargetCharacter;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Function_MetaDataParams[];
#endif
		static const UECodeGen_Private::FFunctionParams FuncParams;
	};
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::NewProp_TargetCharacter = { "TargetCharacter", nullptr, (EPropertyFlags)0x0010000000000080, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(TP_WeaponComponent_eventAttachWeapon_Parms, TargetCharacter), Z_Construct_UClass_AFPSTestCharacter_NoRegister, METADATA_PARAMS(nullptr, 0) };
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::NewProp_TargetCharacter,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::Function_MetaDataParams[] = {
		{ "Category", "Weapon" },
		{ "Comment", "/** Attaches the actor to a FirstPersonCharacter */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Attaches the actor to a FirstPersonCharacter" },
	};
#endif
	const UECodeGen_Private::FFunctionParams Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::FuncParams = { (UObject*(*)())Z_Construct_UClass_UTP_WeaponComponent, nullptr, "AttachWeapon", nullptr, nullptr, sizeof(Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::TP_WeaponComponent_eventAttachWeapon_Parms), Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::PropPointers, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::PropPointers), RF_Public|RF_Transient|RF_MarkAsNative, (EFunctionFlags)0x04020401, 0, 0, METADATA_PARAMS(Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::Function_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::Function_MetaDataParams)) };
	UFunction* Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon()
	{
		static UFunction* ReturnFunction = nullptr;
		if (!ReturnFunction)
		{
			UECodeGen_Private::ConstructUFunction(&ReturnFunction, Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon_Statics::FuncParams);
		}
		return ReturnFunction;
	}
	struct Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics
	{
		struct TP_WeaponComponent_eventEndPlay_Parms
		{
			TEnumAsByte<EEndPlayReason::Type> EndPlayReason;
		};
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_EndPlayReason_MetaData[];
#endif
		static const UECodeGen_Private::FBytePropertyParams NewProp_EndPlayReason;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Function_MetaDataParams[];
#endif
		static const UECodeGen_Private::FFunctionParams FuncParams;
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::NewProp_EndPlayReason_MetaData[] = {
		{ "NativeConst", "" },
	};
#endif
	const UECodeGen_Private::FBytePropertyParams Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::NewProp_EndPlayReason = { "EndPlayReason", nullptr, (EPropertyFlags)0x0010000000000082, UECodeGen_Private::EPropertyGenFlags::Byte, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(TP_WeaponComponent_eventEndPlay_Parms, EndPlayReason), Z_Construct_UEnum_Engine_EEndPlayReason, METADATA_PARAMS(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::NewProp_EndPlayReason_MetaData, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::NewProp_EndPlayReason_MetaData)) }; // 2713016423
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::NewProp_EndPlayReason,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::Function_MetaDataParams[] = {
		{ "Comment", "/** Ends gameplay for this component. */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Ends gameplay for this component." },
	};
#endif
	const UECodeGen_Private::FFunctionParams Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::FuncParams = { (UObject*(*)())Z_Construct_UClass_UTP_WeaponComponent, nullptr, "EndPlay", nullptr, nullptr, sizeof(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::TP_WeaponComponent_eventEndPlay_Parms), Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::PropPointers, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::PropPointers), RF_Public|RF_Transient|RF_MarkAsNative, (EFunctionFlags)0x00080400, 0, 0, METADATA_PARAMS(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::Function_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::Function_MetaDataParams)) };
	UFunction* Z_Construct_UFunction_UTP_WeaponComponent_EndPlay()
	{
		static UFunction* ReturnFunction = nullptr;
		if (!ReturnFunction)
		{
			UECodeGen_Private::ConstructUFunction(&ReturnFunction, Z_Construct_UFunction_UTP_WeaponComponent_EndPlay_Statics::FuncParams);
		}
		return ReturnFunction;
	}
	struct Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics
	{
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Function_MetaDataParams[];
#endif
		static const UECodeGen_Private::FFunctionParams FuncParams;
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics::Function_MetaDataParams[] = {
		{ "Category", "Weapon" },
		{ "Comment", "/** Make the weapon Fire a Projectile */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Make the weapon Fire a Projectile" },
	};
#endif
	const UECodeGen_Private::FFunctionParams Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics::FuncParams = { (UObject*(*)())Z_Construct_UClass_UTP_WeaponComponent, nullptr, "Fire", nullptr, nullptr, 0, nullptr, 0, RF_Public|RF_Transient|RF_MarkAsNative, (EFunctionFlags)0x04020401, 0, 0, METADATA_PARAMS(Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics::Function_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics::Function_MetaDataParams)) };
	UFunction* Z_Construct_UFunction_UTP_WeaponComponent_Fire()
	{
		static UFunction* ReturnFunction = nullptr;
		if (!ReturnFunction)
		{
			UECodeGen_Private::ConstructUFunction(&ReturnFunction, Z_Construct_UFunction_UTP_WeaponComponent_Fire_Statics::FuncParams);
		}
		return ReturnFunction;
	}
	IMPLEMENT_CLASS_NO_AUTO_REGISTRATION(UTP_WeaponComponent);
	UClass* Z_Construct_UClass_UTP_WeaponComponent_NoRegister()
	{
		return UTP_WeaponComponent::StaticClass();
	}
	struct Z_Construct_UClass_UTP_WeaponComponent_Statics
	{
		static UObject* (*const DependentSingletons[])();
		static const FClassFunctionLinkInfo FuncInfo[];
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Class_MetaDataParams[];
#endif
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_ProjectileClass_MetaData[];
#endif
		static const UECodeGen_Private::FClassPropertyParams NewProp_ProjectileClass;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_FireSound_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_FireSound;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_FireAnimation_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_FireAnimation;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_MuzzleOffset_MetaData[];
#endif
		static const UECodeGen_Private::FStructPropertyParams NewProp_MuzzleOffset;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
		static const FCppClassTypeInfoStatic StaticCppClassTypeInfo;
		static const UECodeGen_Private::FClassParams ClassParams;
	};
	UObject* (*const Z_Construct_UClass_UTP_WeaponComponent_Statics::DependentSingletons[])() = {
		(UObject* (*)())Z_Construct_UClass_UActorComponent,
		(UObject* (*)())Z_Construct_UPackage__Script_FPSTest,
	};
	const FClassFunctionLinkInfo Z_Construct_UClass_UTP_WeaponComponent_Statics::FuncInfo[] = {
		{ &Z_Construct_UFunction_UTP_WeaponComponent_AttachWeapon, "AttachWeapon" }, // 3819291838
		{ &Z_Construct_UFunction_UTP_WeaponComponent_EndPlay, "EndPlay" }, // 1634024272
		{ &Z_Construct_UFunction_UTP_WeaponComponent_Fire, "Fire" }, // 3781507124
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_UTP_WeaponComponent_Statics::Class_MetaDataParams[] = {
		{ "BlueprintSpawnableComponent", "" },
		{ "BlueprintType", "true" },
		{ "ClassGroupNames", "Custom" },
		{ "IncludePath", "TP_WeaponComponent.h" },
		{ "IsBlueprintBase", "true" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
	};
#endif
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_ProjectileClass_MetaData[] = {
		{ "Category", "Projectile" },
		{ "Comment", "/** Projectile class to spawn */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Projectile class to spawn" },
	};
#endif
	const UECodeGen_Private::FClassPropertyParams Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_ProjectileClass = { "ProjectileClass", nullptr, (EPropertyFlags)0x0014000000010001, UECodeGen_Private::EPropertyGenFlags::Class, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(UTP_WeaponComponent, ProjectileClass), Z_Construct_UClass_AFPSTestProjectile_NoRegister, Z_Construct_UClass_UClass, METADATA_PARAMS(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_ProjectileClass_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_ProjectileClass_MetaData)) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireSound_MetaData[] = {
		{ "Category", "Gameplay" },
		{ "Comment", "/** Sound to play each time we fire */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Sound to play each time we fire" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireSound = { "FireSound", nullptr, (EPropertyFlags)0x0010000000000005, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(UTP_WeaponComponent, FireSound), Z_Construct_UClass_USoundBase_NoRegister, METADATA_PARAMS(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireSound_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireSound_MetaData)) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireAnimation_MetaData[] = {
		{ "Category", "Gameplay" },
		{ "Comment", "/** AnimMontage to play each time we fire */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "AnimMontage to play each time we fire" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireAnimation = { "FireAnimation", nullptr, (EPropertyFlags)0x0010000000000005, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(UTP_WeaponComponent, FireAnimation), Z_Construct_UClass_UAnimMontage_NoRegister, METADATA_PARAMS(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireAnimation_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireAnimation_MetaData)) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_MuzzleOffset_MetaData[] = {
		{ "Category", "Gameplay" },
		{ "Comment", "/** Gun muzzle's offset from the characters location */" },
		{ "ModuleRelativePath", "TP_WeaponComponent.h" },
		{ "ToolTip", "Gun muzzle's offset from the characters location" },
	};
#endif
	const UECodeGen_Private::FStructPropertyParams Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_MuzzleOffset = { "MuzzleOffset", nullptr, (EPropertyFlags)0x0010000000000005, UECodeGen_Private::EPropertyGenFlags::Struct, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(UTP_WeaponComponent, MuzzleOffset), Z_Construct_UScriptStruct_FVector, METADATA_PARAMS(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_MuzzleOffset_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_MuzzleOffset_MetaData)) };
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UClass_UTP_WeaponComponent_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_ProjectileClass,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireSound,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_FireAnimation,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_UTP_WeaponComponent_Statics::NewProp_MuzzleOffset,
	};
	const FCppClassTypeInfoStatic Z_Construct_UClass_UTP_WeaponComponent_Statics::StaticCppClassTypeInfo = {
		TCppClassTypeTraits<UTP_WeaponComponent>::IsAbstract,
	};
	const UECodeGen_Private::FClassParams Z_Construct_UClass_UTP_WeaponComponent_Statics::ClassParams = {
		&UTP_WeaponComponent::StaticClass,
		"Engine",
		&StaticCppClassTypeInfo,
		DependentSingletons,
		FuncInfo,
		Z_Construct_UClass_UTP_WeaponComponent_Statics::PropPointers,
		nullptr,
		UE_ARRAY_COUNT(DependentSingletons),
		UE_ARRAY_COUNT(FuncInfo),
		UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::PropPointers),
		0,
		0x00B000A4u,
		METADATA_PARAMS(Z_Construct_UClass_UTP_WeaponComponent_Statics::Class_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UClass_UTP_WeaponComponent_Statics::Class_MetaDataParams))
	};
	UClass* Z_Construct_UClass_UTP_WeaponComponent()
	{
		if (!Z_Registration_Info_UClass_UTP_WeaponComponent.OuterSingleton)
		{
			UECodeGen_Private::ConstructUClass(Z_Registration_Info_UClass_UTP_WeaponComponent.OuterSingleton, Z_Construct_UClass_UTP_WeaponComponent_Statics::ClassParams);
		}
		return Z_Registration_Info_UClass_UTP_WeaponComponent.OuterSingleton;
	}
	template<> FPSTEST_API UClass* StaticClass<UTP_WeaponComponent>()
	{
		return UTP_WeaponComponent::StaticClass();
	}
	DEFINE_VTABLE_PTR_HELPER_CTOR(UTP_WeaponComponent);
	struct Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_Statics
	{
		static const FClassRegisterCompiledInInfo ClassInfo[];
	};
	const FClassRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_Statics::ClassInfo[] = {
		{ Z_Construct_UClass_UTP_WeaponComponent, UTP_WeaponComponent::StaticClass, TEXT("UTP_WeaponComponent"), &Z_Registration_Info_UClass_UTP_WeaponComponent, CONSTRUCT_RELOAD_VERSION_INFO(FClassReloadVersionInfo, sizeof(UTP_WeaponComponent), 532226978U) },
	};
	static FRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_3444439679(TEXT("/Script/FPSTest"),
		Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_Statics::ClassInfo, UE_ARRAY_COUNT(Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_TP_WeaponComponent_h_Statics::ClassInfo),
		nullptr, 0,
		nullptr, 0);
PRAGMA_ENABLE_DEPRECATION_WARNINGS
